package activity;

import converters.ModelConverter;
import dynamodb.Appointment;
import dynamodb.AppointmentDao;
import models.AppointmentModel;
import org.apache.logging.log4j.LogManager;
import requests.CreateAppointmentRequest;
import results.CreateAppointmentResult;
import utils.PetProUtils;

import javax.inject.Inject;
import java.util.logging.Logger;

public class CreateAppointmentActivity {
    private final Logger log = (Logger) LogManager.getLogger();
    private final AppointmentDao appDao;

    /**
     * Instantiates a new CreateAppointmmentActivity object.
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
