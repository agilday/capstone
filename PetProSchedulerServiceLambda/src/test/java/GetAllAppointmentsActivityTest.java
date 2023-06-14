import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.activity.GetAllAppointmentsActivity;
import petproschedulerservice.activity.requests.GetAllAppointmentsRequest;
import petproschedulerservice.activity.results.GetAllAppointmentsResult;
import petproschedulerservice.dynamodb.AppointmentDao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetAllAppointmentsActivityTest {
    @Mock
    private AppointmentDao appointmentDao;

    private GetAllAppointmentsActivity getAllAppointmentsActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
        getAllAppointmentsActivity = new GetAllAppointmentsActivity(appointmentDao);
    }

    @Test
    public void handleRequest_getsAllAppointments() {
        //GIVEN
        GetAllAppointmentsRequest request = new GetAllAppointmentsRequest();

        //WHEN
        GetAllAppointmentsResult result = getAllAppointmentsActivity.handleRequest();

        //THEN
        assertNotNull(result.getAllAppointmentsList());
    }
}
