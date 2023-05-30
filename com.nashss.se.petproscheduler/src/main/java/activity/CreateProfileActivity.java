package activity;

import com.amazonaws.auth.profile.internal.Profile;
import converters.ModelConverter;
import dynamodb.ClientProfile;
import dynamodb.ClientProfileDao;
import models.ClientProfileModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CreateProfileRequest;
import results.CreateProfileResult;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;

public class CreateProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final ClientProfileDao profileDao;

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param profileDao PlaylistDao to access the profile table.
     */
    @Inject
    public CreateProfileActivity(ClientProfileDao profileDao) {
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
     * @param createProfileRequest request object containing the profile name and customer ID
     *                              associated with it
     * @return createProfileResult result object containing the API defined {@link ClientProfileModel}
     */
    public CreateProfileResult handleRequest(final CreateProfileRequest createProfileRequest) {
        log.info("Received CreateProfileRequest {}", createProfileRequest);

        if (!MusicPlaylistServiceUtils.isValidString(createProfileRequest.getName())) {
            throw new InvalidAttributeValueException("Playlist name [" + createProfileRequest.getName() +
                    "] contains illegal characters");
        }

        if (!MusicPlaylistServiceUtils.isValidString(createProfileRequest.getCustomerId())) {
            throw new InvalidAttributeValueException("Playlist customer ID [" + createProfileRequest.getCustomerId() +
                    "] contains illegal characters");
        }


        ClientProfile newProfile = new ClientProfile();
        newProfile.setId(MusicPlaylistServiceUtils.generatePlaylistId());
        newProfile.setName(createProfileRequest.getName());
        newProfile.setPhone(createProfileRequest.getPhone());
        newProfile.setNotes(new ArrayList<>());
        newProfile.setPets(new ArrayList<>());

        profileDao.saveProfile(newProfile);

        ClientProfileModel profileModel = new ModelConverter().toProfileModel(newProfile);
        return CreateProfileResult.builder()
                .withProfile(profileModel)
                .build();
    }
}
