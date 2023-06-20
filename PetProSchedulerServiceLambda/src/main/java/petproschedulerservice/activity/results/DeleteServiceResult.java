package petproschedulerservice.activity.results;

import petproschedulerservice.models.ServiceModel;

public class DeleteServiceResult {
    private final ServiceModel serviceModel;
    /**
     * Constructs a new DeleteServiceResult object with the given Service model.
     *
     * @param serviceModel the Service model deleted from the database
     */
    public DeleteServiceResult(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
    }
    /**
     * Returns the Service model deleted from the database.
     *
     * @return the Service model
     */
    public ServiceModel getServiceModel() {
        return serviceModel;
    }
    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "DeleteServiceResult{" +
                "appointmentModel=" + serviceModel +
                '}';
    }

    public static class Builder{
        private ServiceModel serviceModel;

        public DeleteServiceResult.Builder withServiceModel(ServiceModel serviceModel){
            this.serviceModel = serviceModel;
            return this;
        }

        public DeleteServiceResult build(){
            return new DeleteServiceResult(serviceModel);
        }

    }

    public static DeleteServiceResult.Builder builder(){
        return new DeleteServiceResult.Builder();
    }

}
