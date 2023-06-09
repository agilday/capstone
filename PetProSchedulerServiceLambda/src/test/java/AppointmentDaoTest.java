import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.dynamodb.AppointmentDao;

import static org.mockito.MockitoAnnotations.initMocks;

public class AppointmentDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private AppointmentDao appointmentDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        appointmentDao = new appointmentDao(dynamoDBMapper);
    }

    @Test
    void getAllAppointments_withAppointmentsCreated_returnsAllAppointments() {
        //GIVEN

    }

}
