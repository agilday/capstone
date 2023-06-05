package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.CreateAppointmentRequest;
import petproschedulerservice.activity.results.CreateAppointmentResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.Appointment;
import petproschedulerservice.dynamodb.AppointmentDao;
import petproschedulerservice.models.AppointmentModel;
import petproschedulerservice.utils.PetProUtils;

import javax.inject.Inject;

public class CreateAppointmentActivity {
    private final Logger log = LogManager.getLogger();
    private final AppointmentDao appDao;

    /**
     * Instantiates a new CreateAppointmentActivity object.
     *
     * @param appDao AppointmentDao to access the playlists table.
     */
    @Inject
    public CreateAppointmentActivity(AppointmentDao appDao) {
        this.appDao = appDao;
    }

    /**
     * This method handles the incoming request by persisting a new Appointment
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created Appointment.
     * <p>
     * If the provided Appointment name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createAppointmentRequest request object containing the Appointment name and customer ID
     *                              associated with it
     * @return createAppointmentResult result object containing the API defined {@link AppointmentModel}
     */
    public CreateAppointmentResult handleRequest(final CreateAppointmentRequest createAppointmentRequest) {
        log.info("Received CreateAppointmentRequest {}", createAppointmentRequest);

        Appointment newAppointment = new Appointment();
        newAppointment.setId(PetProUtils.generateId());
        newAppointment.setClient(createAppointmentRequest.getClient());
        newAppointment.setDateTime(createAppointmentRequest.getDateTime());
        newAppointment.setPet(createAppointmentRequest.getPet());
        newAppointment.setService(createAppointmentRequest.getService());

        appDao.saveAppointment(newAppointment);

        AppointmentModel appointmentModel = new ModelConverter().toAppointmentModel(newAppointment);
        return CreateAppointmentResult.builder()
                .withAppointment(appointmentModel)
                .build();
    }
}
