package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetClientProfileRequest.Builder.class)
public class GetServiceMenuRequest {

    public GetServiceMenuRequest() {
    }
    /**
     * Returns a new builder for creating instances of GetServiceMenuRequest.
     *
     * @return a new builder for creating instances of GetServiceMenuRequest
     */
    public static Builder builder() {
        return new Builder();
    }
    /**
     * Builder class for creating instances of GetServiceMenuRequest.
     */
    @JsonPOJOBuilder
    public static class Builder {
        /**
         * Creates a new instance of GetServiceMenuRequest using the builder's current configuration.
         *
         * @return a new instance of GetServiceMenuRequest
         */
        public GetServiceMenuRequest build() {
            return new GetServiceMenuRequest();}
    }
}
