package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = CreateAppointmentRequest.Builder.class)
public class CreateAppointmentRequest {
    private final String client;
    private final String dateTime;
    private final String pet;
    private final String service;

    private CreateAppointmentRequest(String client, String dateTime, String pet, String service) {
        this.client = client;
        this.dateTime = dateTime;
        this.pet = pet;
        this.service = service;
    }

    public String getClient() {
        return client;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getPet() {
        return pet;
    }

    public String getService() {
        return service;
    }

    @Override
    public String toString() {
        return "CreateAppointmentRequest{" +
                ", client='" + client + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", pet=" + pet + '\'' +
                ", service=" + service +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String client;
        private String dateTime;
        private String pet;
        private String service;


        public Builder withClient(String client) {
            this.client = client;
            return this;
        }

        public Builder withDateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder withPet(String pet) {
            this.pet = pet;
            return this;
        }

        public Builder withService(String service) {
            this.service = service;
            return this;
        }

        public CreateAppointmentRequest build() {
            return new CreateAppointmentRequest(client, dateTime, pet, service);
        }
    }
}