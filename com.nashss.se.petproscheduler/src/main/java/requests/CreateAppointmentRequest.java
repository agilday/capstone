package requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

//import static com.nashss.se.musicplaylistservice.utils.CollectionUtils.copyToList;

@JsonDeserialize(builder = CreateAppointmentRequest.Builder.class)
public class CreateAppointmentRequest {
    private final String name;
    private final String customerId;
    private final String customerName;
    private final List<String> tags;

    private CreateAppointmentRequest(String name, String customerId, String customerName, List<String> tags) {
        this.name = name;
        this.customerId = customerId;
        this.customerName = customerName;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getTags() {
        return copyToList(tags);
    }

    @Override
    public String toString() {
        return "CreateAppointmentRequest{" +
                "name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", tags=" + tags +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String customerId;
        private String customerName;
        private List<String> tags;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder withTags(List<String> tags) {
            this.tags = copyToList(tags);
            return this;
        }

        public CreateAppointmentRequest build() {
            return new CreateAppointmentRequest(name, customerId, customerName, tags);
        }
    }
}