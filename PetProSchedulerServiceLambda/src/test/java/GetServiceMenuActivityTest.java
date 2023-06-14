import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.activity.GetServiceMenuActivity;
import petproschedulerservice.activity.requests.GetServiceMenuRequest;
import petproschedulerservice.activity.results.GetServiceMenuResult;
import petproschedulerservice.dynamodb.ServiceDao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetServiceMenuActivityTest {

    @Mock
    private ServiceDao serviceDao;

    private GetServiceMenuActivity getServiceMenuActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
        getServiceMenuActivity = new GetServiceMenuActivity(serviceDao);
    }

    @Test
    public void handleRequest_getsAllServices() {
        //GIVEN
        GetServiceMenuRequest request = new GetServiceMenuRequest();

        //WHEN
        GetServiceMenuResult result = getServiceMenuActivity.handleRequest();

        //THEN
        assertNotNull(result.getAllServices());


    }
}
