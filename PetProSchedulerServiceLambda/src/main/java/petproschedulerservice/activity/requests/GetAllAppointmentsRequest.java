package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetAllAppointmentsRequest.Builder.class)
public class GetAllAppointmentsRequest {

    public GetAllAppointmentsRequest() {
    }

    /**
     * Returns a new builder for creating instances of GetAllAppointmentsRequest.
     *
     * @return a new builder for creating instances of GetAllAppointmentsRequest
     */
    public static GetAllAppointmentsRequest.Builder builder() {
        return new GetAllAppointmentsRequest.Builder();
    }

    /**
     * Builder class for creating instances of GetAllAppointmentsRequest.
     */
    @JsonPOJOBuilder
    public static class Builder {

        public GetAllAppointmentsRequest build() {
            return new GetAllAppointmentsRequest();
        }
    }
}
