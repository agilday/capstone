package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.GetAllClientProfilesRequest;
import petproschedulerservice.activity.results.GetAllClientProfilesResult;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;

import javax.inject.Inject;
import java.util.List;

public class GetAllClientProfilesActivity {

    private final Logger log = LogManager.getLogger();
    private final ClientProfileDao clientProfileDao;

    @Inject
    public GetAllClientProfilesActivity(ClientProfileDao clientProfileDao) {
        this.clientProfileDao = clientProfileDao;
    }
    /**
     * Handles a {@link GetAllClientProfilesRequest} and returns a {@link GetAllClientProfilesResult} containing a list of all client profiles.
     *
     * @return a {@link GetAllClientProfilesResult} containing a list of all client profiles
     */
    public GetAllClientProfilesResult handleRequest(){
        log.info("Receive GetAllClientProfilesRequest {} ", "called Get All Client Profiles");

        List<ClientProfile> listClientProfiles = clientProfileDao.getAllClientProfiles();

        return GetAllClientProfilesResult.builder()
                .withClientProfilesList(listClientProfiles)
                .build();
    }
}
