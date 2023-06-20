package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.DeleteAppointmentRequest;
import petproschedulerservice.activity.results.DeleteAppointmentResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.Appointment;
import petproschedulerservice.dynamodb.AppointmentDao;
import petproschedulerservice.models.AppointmentModel;

import javax.inject.Inject;

public class DeleteAppointmentActivity {
    private final Logger log = LogManager.getLogger();
    private final AppointmentDao appointmentDao;

    @Inject
    public DeleteAppointmentActivity(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }
    /**
     * Handles a request for deleting an appointment by its ID.
     *
     * @param deleteAppointmentRequest the request object containing the appointment ID.
     * @return a {@code DeleteAppointmentResult} object containing the deleted appointment.
     */
    public DeleteAppointmentResult handleRequest(final DeleteAppointmentRequest deleteAppointmentRequest){
        log.info("Receive DeleteAppointmentResult {} ", deleteAppointmentRequest);


        String id = deleteAppointmentRequest.getId();
        Appointment appointment = appointmentDao.getAppointment(id);

        AppointmentModel appointmentModel = new ModelConverter().toAppointmentModel(appointment);

        return DeleteAppointmentResult.builder()
                .withAppointmentModel(appointmentModel)
                .build();
    }
}
