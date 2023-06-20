package petproschedulerservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import petproschedulerservice.exceptions.AppointmentNotFoundException;
import petproschedulerservice.exceptions.ProfileNotFoundException;
import petproschedulerservice.metrics.MetricsConstants;
import petproschedulerservice.metrics.MetricsPublisher;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the playlists table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public AppointmentDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns all {@link Appointment}.
     *
     * @return list of Appointments, or empty list if none were found.
     */
    public List<Appointment> getAllAppointments() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Appointment> appointmentsList = dynamoDbMapper.scan(Appointment.class, scanExpression);
        return appointmentsList;
    }
    /**
     * Returns the {@link Appointment} corresponding to the specified id.
     *
     * @param id the Appointment ID
     * @return the stored Appointment, or null if none was found.
     */
    public Appointment getAppointment(String id) {
        Appointment appointment = this.dynamoDbMapper.load(Appointment.class, id);

        if (appointment == null) {
            throw new AppointmentNotFoundException("Could not find Appointment with id " + id);
        }
        return appointment;
    }

    /**
     * Saves (creates or updates) the given appointment.
     *
     * @param appointment The appointment to save
     * @return The Appointment object that was saved
     */
    public void saveAppointment(Appointment appointment) {
        this.dynamoDbMapper.save(appointment);
    }

    /**
     * Deletes (creates or updates) the given appointment.
     *
     * @param appointment The appointment to delete
     * @return The Appointment object that was deleted
     */
    public void deleteAppointment(Appointment appointment) {
        this.dynamoDbMapper.delete(appointment);
    }


    /**
     * Perform a search (via a "scan") of the playlist table for appointments matching the given criteria.
     *
     * Both "appointmentName" and "tags" attributes are searched.
     * The criteria are an array of Strings. Each element of the array is search individually.
     * ALL elements of the criteria array must appear in the playlistName or the tags (or both).
     * Searches are CASE SENSITIVE.
     *
     * @param criteria an array of String containing search criteria.
     * @return a List of Playlist objects that match the search criteria.
     */
    public List<Appointment> searchAppointment(String[] criteria) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();

        if (criteria.length > 0) {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            String valueMapNamePrefix = ":c";

            StringBuilder nameFilterExpression = new StringBuilder();
            StringBuilder tagsFilterExpression = new StringBuilder();

            for (int i = 0; i < criteria.length; i++) {
                valueMap.put(valueMapNamePrefix + i,
                        new AttributeValue().withS(criteria[i]));
                nameFilterExpression.append(
                        filterExpressionPart("playlistName", valueMapNamePrefix, i));
                tagsFilterExpression.append(
                        filterExpressionPart("tags", valueMapNamePrefix, i));
            }

            dynamoDBScanExpression.setExpressionAttributeValues(valueMap);
            dynamoDBScanExpression.setFilterExpression(
                    "(" + nameFilterExpression + ") or (" + tagsFilterExpression + ")");
        }

        return this.dynamoDbMapper.scan(Appointment.class, dynamoDBScanExpression);
    }

    private StringBuilder filterExpressionPart(String target, String valueMapNamePrefix, int position) {
        String possiblyAnd = position == 0 ? "" : "and ";
        return new StringBuilder()
                .append(possiblyAnd)
                .append("contains(")
                .append(target)
                .append(", ")
                .append(valueMapNamePrefix).append(position)
                .append(") ");
    }
}
