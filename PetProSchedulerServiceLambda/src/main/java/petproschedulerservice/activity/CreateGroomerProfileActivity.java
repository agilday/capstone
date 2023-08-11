package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.CreateClientProfileRequest;
import petproschedulerservice.activity.requests.CreateGroomerProfileRequest;
import petproschedulerservice.activity.results.CreateClientProfileResult;
import petproschedulerservice.activity.results.CreateGroomerProfileResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.dynamodb.GroomerProfile;
import petproschedulerservice.dynamodb.GroomerProfileDao;
import petproschedulerservice.models.ClientProfileModel;
import petproschedulerservice.models.GroomerProfileModel;
import petproschedulerservice.utils.PetProUtils;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.List;

public class CreateGroomerProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final GroomerProfileDao profileDao;

    /**
     * Instantiates a new CreateGroomerProfileActivity object.
     *
     * @param profileDao GroomerProfileDao to access the clientprofiles table.
     */
    @Inject
    public CreateGroomerProfileActivity(GroomerProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    /**
     * This method handles the incoming request by persisting a new profile
     * with the provided profile name from the request.
     * <p>
     * It then returns the newly created profile.
     * <p>
     * If the provided profile name has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createGroomerProfileRequest request object containing the profile name and customer ID
     *                              associated with it
     * @return createGroomerProfileResult result object containing the API defined {@link GroomerProfileModel}
     */
    public CreateGroomerProfileResult handleRequest(final CreateGroomerProfileRequest createGroomerProfileRequest) throws InvalidAttributeValueException {
        log.info("Received CreateGroomerProfileRequest {}", createGroomerProfileRequest);

        if (!PetProUtils.isValidString(createGroomerProfileRequest.getLname())) {
            throw new InvalidAttributeValueException("Groomer name [" + createGroomerProfileRequest.getLname() +
                    "] contains illegal characters");
        }
        List<String> notes = null;
        if(createGroomerProfileRequest.getNotes() != null) {
            notes = new ArrayList<>(createGroomerProfileRequest.getNotes());
        }

        GroomerProfile newProfile = new GroomerProfile();
        newProfile.setLname(createGroomerProfileRequest.getLname());
        newProfile.setFname(createGroomerProfileRequest.getFname());
        newProfile.setPhone(createGroomerProfileRequest.getPhone());
        newProfile.setAvailability(createGroomerProfileRequest.getAvailability());
        newProfile.setNotes(notes);
        newProfile.setGroomsCats(createGroomerProfileRequest.getGroomsCats());

        profileDao.saveGroomerProfile(newProfile);

        GroomerProfileModel profileModel = new ModelConverter().toGroomerProfileModel(newProfile);
        return CreateGroomerProfileResult.builder()
                .withProfile(profileModel)
                .build();
    }
}
