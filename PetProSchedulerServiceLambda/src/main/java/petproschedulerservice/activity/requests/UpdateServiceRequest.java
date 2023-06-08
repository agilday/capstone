package petproschedulerservice.activity.requests;

import com.amazonaws.internal.config.Builder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateServiceRequest.Builder.class)
public class UpdateServiceRequest {

    private final String title;
    private final String description;

    private UpdateServiceRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Returns the title of the service.
     *
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of the service.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "UpdateServiceRequest{" +
                "title='" + title + '\'' +
                "description='" + description +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String title;
        private String description;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public UpdateServiceRequest build() {
            return new UpdateServiceRequest(title, description);
        }
    }
}
