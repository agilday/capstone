package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.CreateClientProfileRequest;
import petproschedulerservice.activity.results.CreateClientProfileResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.models.ClientProfileModel;
import petproschedulerservice.utils.PetProUtils;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;

public class CreateClientProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final ClientProfileDao profileDao;

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param profileDao PlaylistDao to access the profile table.
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
        log.info("Received CreateProfileRequest {}", createClientProfileRequest);

        if (!PetProUtils.isValidString(createClientProfileRequest.getName())) {
            throw new InvalidAttributeValueException("Playlist name [" + createClientProfileRequest.getName() +
                    "] contains illegal characters");
        }

        if (!PetProUtils.isValidString(createClientProfileRequest.getId())) {
            throw new InvalidAttributeValueException("Playlist customer ID [" + createClientProfileRequest.getId() +
                    "] contains illegal characters");
        }


        ClientProfile newProfile = new ClientProfile();
        newProfile.setId(PetProUtils.generateId());
        newProfile.setName(createClientProfileRequest.getName());
        newProfile.setPhone(createClientProfileRequest.getPhone());
        newProfile.setAddress(createClientProfileRequest.getAddress());
        newProfile.setNotes(new ArrayList<>());
        newProfile.setPets(new ArrayList<>());

        profileDao.saveClientProfile(newProfile);

        ClientProfileModel profileModel = new ModelConverter().toClientProfileModel(newProfile);
        return CreateClientProfileResult.builder()
                .withProfile(profileModel)
                .build();
    }
}
