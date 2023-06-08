package petproschedulerservice.models;


import java.util.Objects;

public class ServiceModel {
    private final String title;
    private final String description;

    private ServiceModel(String title, String description) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceModel that = (ServiceModel) o;

        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    //CHECKSTYLE:OFF:Builder
    public static ServiceModel.Builder builder() {
        return new ServiceModel.Builder();
    }


    public static class Builder {
        private String title;
        private String description;

        public ServiceModel.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ServiceModel.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ServiceModel build() {
            return new ServiceModel(title, description);
        }
    }

}
