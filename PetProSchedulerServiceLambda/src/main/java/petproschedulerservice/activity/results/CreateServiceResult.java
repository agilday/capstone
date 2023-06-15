package petproschedulerservice.activity.results;

import com.amazonaws.internal.config.Builder;
import petproschedulerservice.models.ServiceModel;

public class CreateServiceResult {

    private final ServiceModel service;

    private CreateServiceResult(ServiceModel service) {
        this.service = service;
    }

    public ServiceModel getService() {
        return service;
    }

    @Override
    public String toString() {
        return "CreateServiceResult{" +
                "service=" + service +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ServiceModel service;

        public Builder withService(ServiceModel service) {
            this.service = service;
            return this;
        }

        public CreateServiceResult build() {
            return new CreateServiceResult(service);
        }
    }
}
