package petproschedulerservice.models;

import java.util.List;
import java.util.Objects;

public class AppointmentModel {
    private final String id;
    private final String client;
    private final String dateTime;
    private final String pet;
    private final String service;

    private AppointmentModel(String id, String client, String dateTime,
                          String pet, String service) {
        this.id = id;
        this.client = client;
        this.dateTime = dateTime;
        this.pet = pet;
        this.service = service;
    }

    public String getId() {
        return id;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppointmentModel that = (AppointmentModel) o;

        return Objects.equals(id, that.id) && Objects.equals(client, that.client) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(pet, that.pet) &&
                Objects.equals(service, that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, dateTime, pet, service);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String client;
        private String dateTime;
        private String pet;
        private String service;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

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

        public AppointmentModel build() {
            return new AppointmentModel(id, client, dateTime, pet, service);
        }
    }
}
