import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.dynamodb.Appointment;
import petproschedulerservice.dynamodb.AppointmentDao;
import petproschedulerservice.metrics.MetricsPublisher;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AppointmentDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;
    private AppointmentDao target;

    @BeforeEach
    public void setup() {
        initMocks(this);
        target = new AppointmentDao(dynamoDBMapper, metricsPublisher);
    }


    @Test
    void saveAppointment_givenAppointmentToSave_savesAppointment() {
        //GIVEN
        Appointment app1 = new Appointment();
        app1.setId("123");
        app1.setClient("ashley");
        app1.setDateTime("08/01/2023 7:00am");
        app1.setPet("Scotch");
        app1.setService("wash and blow dry");

        doNothing().when(dynamoDBMapper).save(app1);

        //WHEN
        target.saveAppointment(app1);

        //THEN
        verify(dynamoDBMapper).save(app1);

    }

    @Test
    void deleteAppointment_givenAppointmentToDelete_deletesAppointment() {
        //GIVEN
        Appointment app1 = new Appointment();
        app1.setId("123");
        app1.setClient("ashley");
        app1.setDateTime("08/01/2023 7:00am");
        app1.setPet("Scotch");
        app1.setService("wash and blow dry");

        doNothing().when(dynamoDBMapper).delete(app1);

        //WHEN
        target.deleteAppointment(app1);

        //THEN
        verify(dynamoDBMapper).delete(app1);

    }

}
