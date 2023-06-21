import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.activity.UpdateServiceActivity;
import petproschedulerservice.activity.requests.UpdateServiceRequest;
import petproschedulerservice.activity.results.UpdateServiceResult;
import petproschedulerservice.dynamodb.Service;
import petproschedulerservice.dynamodb.ServiceDao;
import petproschedulerservice.metrics.MetricsPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateServiceActivityTest {

    @Mock
    private ServiceDao serviceDao;

    @Mock
    private MetricsPublisher metricsPublisher;

    private UpdateServiceActivity updateServiceActivity;

    @BeforeEach
    public void setup() {
        openMocks(this);
        updateServiceActivity = new UpdateServiceActivity(serviceDao, metricsPublisher);
    }

    @Test
    void handleRequest_updatesService() {
        //GIVEN
        String title = "title";
        String description = "description";

        UpdateServiceRequest request = UpdateServiceRequest.builder()
                .withTitle(title)
                .withDescription(description)
                .build();

        Service service = new Service();
        service.setTitle(title);
        service.setDescription(description);

        when(serviceDao.getService(title)).thenReturn(service);
        when(serviceDao.saveService(service)).thenReturn(service);

        //WHEN
        UpdateServiceResult result = updateServiceActivity.handleRequest(request);

        //THEN
        assertEquals(title, result.getServiceModel().getTitle());
        assertEquals(description, result.getServiceModel().getDescription());
    }
}
