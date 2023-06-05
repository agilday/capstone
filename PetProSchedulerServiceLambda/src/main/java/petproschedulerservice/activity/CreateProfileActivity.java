//package petproschedulerservice.activity;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import petproschedulerservice.activity.requests.CreateProfileRequest;
//import petproschedulerservice.activity.results.CreateProfileResult;
//import petproschedulerservice.converters.ModelConverter;
//import petproschedulerservice.dynamodb.ClientProfile;
//import petproschedulerservice.dynamodb.ClientProfileDao;
//import petproschedulerservice.models.ClientProfileModel;
//import petproschedulerservice.utils.PetProUtils;
//
//import javax.inject.Inject;
//import javax.management.InvalidAttributeValueException;
//import java.util.ArrayList;
//
//public class CreateProfileActivity {
//    private final Logger log = LogManager.getLogger();
//    private final ClientProfileDao profileDao;
//
//    /**
//     * Instantiates a new CreatePlaylistActivity object.
//     *
//     * @param profileDao PlaylistDao to access the profile table.
//     */
//    @Inject
//    public CreateProfileActivity(ClientProfileDao profileDao) {
//        this.profileDao = profileDao;
//    }
//
//    /**
//     * This method handles the incoming request by persisting a new profile
//     * with the provided profile name and customer ID from the request.
//     * <p>
//     * It then returns the newly created profile.
//     * <p>
//     * If the provided profile name or customer ID has invalid characters, throws an
//     * InvalidAttributeValueException
//     *
//     * @param createProfileRequest request object containing the profile name and customer ID
//     *                              associated with it
//     * @return createProfileResult result object containing the API defined {@link ClientProfileModel}
//     */
//    public CreateProfileResult handleRequest(final CreateProfileRequest createProfileRequest) throws InvalidAttributeValueException {
//        log.info("Received CreateProfileRequest {}", createProfileRequest);
//
//        if (!PetProUtils.isValidString(createProfileRequest.getName())) {
//            throw new InvalidAttributeValueException("Playlist name [" + createProfileRequest.getName() +
//                    "] contains illegal characters");
//        }
//
//        if (!PetProUtils.isValidString(createProfileRequest.getId())) {
//            throw new InvalidAttributeValueException("Playlist customer ID [" + createProfileRequest.getId() +
//                    "] contains illegal characters");
//        }
//
//
//        ClientProfile newProfile = new ClientProfile();
//        newProfile.setId(PetProUtils.generateId());
//        newProfile.setName(createProfileRequest.getName());
//        newProfile.setPhone(createProfileRequest.getPhone());
//        newProfile.setNotes(new ArrayList<>());
//        newProfile.setPets(new ArrayList<>());
//
//        profileDao.saveClientProfile(newProfile);
//
//        ClientProfileModel profileModel = new ModelConverter().toProfileModel(newProfile);
//        return CreateProfileResult.builder()
//                .withProfile(profileModel)
//                .build();
//    }
//}
