package petproschedulerservice.activity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.GetClientProfileRequest;
import petproschedulerservice.activity.results.GetClientProfileResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.models.ClientProfileModel;

import javax.inject.Inject;

public class GetClientProfileActivity {

    private final Logger log = LogManager.getLogger();
    private final ClientProfileDao profileDao;

    @Inject
    public GetClientProfileActivity(ClientProfileDao profileDao) {
        this.profileDao = profileDao;
    }
    /**
     * Handles a request for getting a user profile by its ID.
     *
     * @param getClientProfileRequest the request object containing the profile ID.
     * @return a {@code GetProfileResult} object containing the retrieved user profile.
     */
    public GetClientProfileResult handleRequest(final GetClientProfileRequest getClientProfileRequest){
        log.info("Receive GetClientProfileResult {} ", getClientProfileRequest);


        String id = getClientProfileRequest.getProfileId();
        ClientProfile profile = profileDao.getClientProfile(id);

        ClientProfileModel profileModel = new ModelConverter().toClientProfileModel(profile);

        return GetClientProfileResult.builder()
                .withClientProfileModel(profileModel)
                .build();
    }

}
