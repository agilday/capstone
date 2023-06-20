package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetAllAppointmentsRequest.Builder.class)
public class DeleteAppointmentRequest {

    private final String id;

    /**
     * Constructs a new DeleteAppointmentRequest with the given ID.
     *
     * @param id the ID of the appointment to delete
     */
    public DeleteAppointmentRequest(String id) {
        this.id = id;
    }
    /**
     * Returns the ID of the appointment to delete.
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a string representation of this DeleteAppointmentRequest.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "DeleteAppointmentRequest{" +
                "Id='" + id + '\'' +
                '}';
    }
    /**
     * Builder class for constructing DeleteAppointmentRequest objects.
     */
    @JsonPOJOBuilder
    public static class Builder{

        private String id;


        public DeleteAppointmentRequest.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public DeleteAppointmentRequest build(){
            return new DeleteAppointmentRequest(id);
        }

    }

    public static DeleteAppointmentRequest.Builder builder(){
        return new DeleteAppointmentRequest.Builder();
    }
}
