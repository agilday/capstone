package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.results.GetAllAppointmentsResult;
import petproschedulerservice.dynamodb.Appointment;
import petproschedulerservice.dynamodb.AppointmentDao;

import javax.inject.Inject;
import java.util.List;

public class GetAllAppointmentsActivity {

    private final Logger log = LogManager.getLogger();
    private final AppointmentDao appointmentDao;

    @Inject
    public GetAllAppointmentsActivity(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }
    /**
     * Handles a {@link GetAllAppointmentsRequest} and returns a {@link GetAllAppointmentsResult} containing a list of all appointments.
     *
     * @return a {@link GetAllAppointmentsResult} containing a list of all appointments
     */
    public GetAllAppointmentsResult handleRequest(){
        log.info("Receive GetAllAppointmentsRequest {} ", "called Get All Appointments");

        List<Appointment> listAppointments = appointmentDao.getAllAppointments();

        return GetAllAppointmentsResult.builder()
                .withAppointmentsList(listAppointments)
                .build();
    }
}