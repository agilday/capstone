package petproschedulerservice.activity.requests;

import com.amazonaws.internal.config.Builder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetAllClientProfilesRequest.Builder.class)
public class GetAllClientProfilesRequest {

    public GetAllClientProfilesRequest() {
    }

    /**
     * Returns a new builder for creating instances of GetAllClientProfilesRequest.
     *
     * @return a new builder for creating instances of GetAllClientProfilesRequest
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for creating instances of GetAllClientProfilesRequest.
     */
    @JsonPOJOBuilder
    public static class Builder {

        public GetAllClientProfilesRequest build() {
            return new GetAllClientProfilesRequest();
        }
    }
}
