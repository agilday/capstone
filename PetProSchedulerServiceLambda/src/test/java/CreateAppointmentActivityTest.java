import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.activity.CreateAppointmentActivity;
import petproschedulerservice.activity.requests.CreateAppointmentRequest;
import petproschedulerservice.activity.results.CreateAppointmentResult;
import petproschedulerservice.dynamodb.Appointment;
import petproschedulerservice.dynamodb.AppointmentDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateAppointmentActivityTest {

    @Mock
    private AppointmentDao appointmentDao;

    private CreateAppointmentActivity createAppointmentActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createAppointmentActivity = new CreateAppointmentActivity(appointmentDao);
    }

    @Test
    public void handleRequest_createsNewAppointment() {
        //GIVEN
        String expectedClient = "john";
        String expectedDateTime = "09/01/23 2:00pm";
        String expectedPet = "spot";
        String expectedService = "full groom";

        CreateAppointmentRequest request = CreateAppointmentRequest.builder()
                .withClient(expectedClient)
                .withDateTime(expectedDateTime)
                .withPet(expectedPet)
                .withService(expectedService)
                .build();

        //WHEN
        CreateAppointmentResult result = createAppointmentActivity.handleRequest(request);

        //THEN
        verify(appointmentDao).saveAppointment(any(Appointment.class));

        assertNotNull(result.getAppointment().getId());
        assertEquals(expectedClient, result.getAppointment().getClient());
        assertEquals(expectedDateTime, result.getAppointment().getDateTime());
        assertEquals(expectedPet, result.getAppointment().getPet());
        assertEquals(expectedService, result.getAppointment().getService());
    }
}
