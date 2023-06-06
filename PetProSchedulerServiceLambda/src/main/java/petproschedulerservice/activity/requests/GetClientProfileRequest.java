package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetClientProfileRequest.Builder.class)
public class GetClientProfileRequest {
    private final String profileId;
    /**
     * Constructs a new GetClientProfileRequest with the given profile ID.
     *
     * @param profileId the ID of the profile to get
     */
    public GetClientProfileRequest(String profileId) {
        this.profileId = profileId;
    }
    /**
     * Returns the ID of the profile to get.
     *
     * @return the profile ID
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Returns a string representation of this GetClientProfileRequest.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "GetClientProfileRequest{" +
                "profileId='" + profileId + '\'' +
                '}';
    }
    /**
     * Builder class for constructing GetClientProfileRequest objects.
     */
    @JsonPOJOBuilder
    public static class Builder{

        private String profileId;


        public Builder withId(String profileId) {
            this.profileId = profileId;
            return this;
        }

        public GetClientProfileRequest build(){
            return new GetClientProfileRequest(profileId);
        }

    }

    public static Builder builder(){
        return new Builder();
    }

}
