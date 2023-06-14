import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.dynamodb.Service;
import petproschedulerservice.dynamodb.ServiceDao;
import petproschedulerservice.metrics.MetricsPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ServiceDaoTest {

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private ServiceDao target;

    @BeforeEach
    public void setup() {
        initMocks(this);
        target = new ServiceDao(mapper, metricsPublisher);
    }

    @Test
    void getService_withTitle_returnsService() {
        //GIVEN
        String title = "nail trim";
        String description = "gentle nail trim and file";
        Service service = new Service();
        service.setTitle(title);
        service.setDescription(description);

        when(mapper.load(Service.class, title)).thenReturn(service);

        //WHEN
        Service result = target.getService(title);

        //THEN
        verify(mapper).load(Service.class, title);
        assertEquals(service, result);
    }
//
//    @Test
//    void saveService_givenService_savesService() {
//        //GIVEN
//        Service service = new Service();
//        service.setTitle("full groom");
//        service.setDescription("wash, dry, nail trim, ear cleaning, and cologne.");
//
//        doNothing().when(mapper).save(service);
//
//        //WHEN
//        Service result = target.saveService(service.getTitle(), service.getDescription());
//
//        //THEN
//        verify(mapper).save(service);
//        assertEquals(service, result);
//    }
}
