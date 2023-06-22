package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.CreateClientProfileRequest;
import petproschedulerservice.activity.results.CreateClientProfileResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.dynamodb.Pet;
import petproschedulerservice.models.ClientProfileModel;
import petproschedulerservice.utils.PetProUtils;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.List;

public class CreateClientProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final ClientProfileDao profileDao;

    /**
     * Instantiates a new CreateClientProfileActivity object.
     *
     * @param profileDao ClientProfileDao to access the clientprofiles table.
     */
    @Inject
    public CreateClientProfileActivity(ClientProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    /**
     * This method handles the incoming request by persisting a new profile
     * with the provided profile name and customer ID from the request.
     * <p>
     * It then returns the newly created profile.
     * <p>
     * If the provided profile name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createClientProfileRequest request object containing the profile name and customer ID
     *                              associated with it
     * @return createProfileResult result object containing the API defined {@link ClientProfileModel}
     */
    public CreateClientProfileResult handleRequest(final CreateClientProfileRequest createClientProfileRequest) throws InvalidAttributeValueException {
        log.info("Received CreateClientProfileRequest {}", createClientProfileRequest);

        if (!PetProUtils.isValidString(createClientProfileRequest.getName())) {
            throw new InvalidAttributeValueException("Client name [" + createClientProfileRequest.getName() +
                    "] contains illegal characters");
        }
        List<String> notes = null;
        if(createClientProfileRequest.getNotes() != null) {
            notes = new ArrayList<>(createClientProfileRequest.getNotes());
        }
        List<String> pets = null;
        if(createClientProfileRequest.getPets() != null) {
            pets = new ArrayList<>(createClientProfileRequest.getPets());
        }

        ClientProfile newProfile = new ClientProfile();
        newProfile.setId(PetProUtils.generateId());
        newProfile.setName(createClientProfileRequest.getName());
        newProfile.setPhone(createClientProfileRequest.getPhone());
        newProfile.setAddress(createClientProfileRequest.getAddress());
        newProfile.setNotes(notes);
        newProfile.setPets(pets);

        profileDao.saveClientProfile(newProfile);

        ClientProfileModel profileModel = new ModelConverter().toClientProfileModel(newProfile);
        return CreateClientProfileResult.builder()
                .withProfile(profileModel)
                .build();
    }
}
