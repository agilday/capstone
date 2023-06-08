package petproschedulerservice.activity.results;

import petproschedulerservice.models.ServiceModel;

public class UpdateServiceResult {

    private final ServiceModel serviceModel;

    /**
     * Constructs a new UpdateServiceResult object with the specified service model.
     * @param serviceModel The service model that was updated.
     */
    public UpdateServiceResult(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
    }

    /**
     * Returns the service model that was updated.
     * @return The updated service model.
     */
    public ServiceModel getServiceModel() {
        return serviceModel;
    }

    @Override
    public String toString() {
        return "UpdateServiceResult{" +
                "serviceModel=" + serviceModel +
                '}';
    }
    /**
     * Builder class for constructing UpdateServiceResult objects.
     */
    public static class Builder{
        private ServiceModel serviceModel;

        public Builder withService(ServiceModel serviceModel){
            this.serviceModel = serviceModel;
            return this;
        }

        public UpdateServiceResult build(){
            return new UpdateServiceResult(serviceModel);
        }

    }

    public static Builder builder(){
        return new Builder();
    }

}
