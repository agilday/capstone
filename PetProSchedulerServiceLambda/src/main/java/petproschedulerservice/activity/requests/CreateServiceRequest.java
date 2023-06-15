package petproschedulerservice.activity.requests;

import com.amazonaws.internal.config.Builder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateServiceRequest.Builder.class)
public class CreateServiceRequest {
    private final String title;
    private final String description;

    private CreateServiceRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "CreateServiceRequest{" +
                ", title='" +title + '\'' +
                ", description='" + description +
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

        public CreateServiceRequest build() {
            return new CreateServiceRequest(title, description);
        }
    }
}
