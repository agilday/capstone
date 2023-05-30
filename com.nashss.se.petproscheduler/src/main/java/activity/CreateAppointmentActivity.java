package activity;

import converters.ModelConverter;
import dynamodb.AppointmentDao;
import models.AppointmentModel;
import org.apache.logging.log4j.LogManager;
import requests.CreateAppointmentRequest;
import results.CreateAppointmentResult;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class CreateAppointmentActivity {
    private final Logger log = LogManager.getLogger();
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

        if (!MusicPlaylistServiceUtils.isValidString(createAppointmentRequest.getName())) {
            throw new InvalidAttributeValueException("Playlist name [" + createAppointmentRequest.getName() +
                    "] contains illegal characters");
        }

        if (!MusicPlaylistServiceUtils.isValidString(createAppointmentRequest.getCustomerId())) {
            throw new InvalidAttributeValueException("Playlist customer ID [" + createAppointmentRequest.getCustomerId() +
                    "] contains illegal characters");
        }

        Set<String> playlistTags = null;
        if (createAppointmentRequest.getTags() != null) {
            playlistTags = new HashSet<>(createAppointmentRequest.getTags());
        }

        Appointment newAppointment = new Appointment();
        newAppointment.setId(MusicPlaylistServiceUtils.generatePlaylistId());
        newAppointment.setName(createAppointmentRequest.getName());
        newAppointment.setCustomerId(createAppointmentRequest.getCustomerId());
        newAppointment.setCustomerName(createAppointmentRequest.getCustomerName());
        newAppointment.setSongCount(0);
        newAppointment.setTags(playlistTags);
        newAppointment.setSongList(new ArrayList<>());

        appDao.saveAppointment(newAppointment);

        AppointmentModel appointmentModel = new ModelConverter().toAppointmentModel(newAppointment);
        return CreateAppointmentResult.builder()
                .withAppointment(appointmentModel)
                .build();
    }
}
