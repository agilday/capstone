import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.activity.GetAllClientProfilesActivity;
import petproschedulerservice.activity.requests.GetAllClientProfilesRequest;
import petproschedulerservice.activity.results.GetAllClientProfilesResult;
import petproschedulerservice.dynamodb.ClientProfileDao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllClientProfilesActivityTest {

    @Mock
    private ClientProfileDao profileDao;

    private GetAllClientProfilesActivity getAllClientProfilesActivity;

    @BeforeEach
    public void setup() {
        openMocks(this);
        getAllClientProfilesActivity = new GetAllClientProfilesActivity(profileDao);
    }

    @Test
    public void handleRequest_getsAllCLientProfiles() {
        //GIVEN
        GetAllClientProfilesRequest request = new GetAllClientProfilesRequest();

        //WHEN
        GetAllClientProfilesResult result = getAllClientProfilesActivity.handleRequest();

        //THEN
        assertNotNull(result.getAllClientProfilesList());

    }
}
