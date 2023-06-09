package petproschedulerservice.activity.results;

import petproschedulerservice.models.ClientProfileModel;

public class UpdateClientProfileResult {
    private final ClientProfileModel clientProfileModel;

    /**
     * Constructs a new UpdateClientProfileResult object with the specified client Profile Model.
     * @param clientProfileModel The client Profile Model that was updated.
     */
    public UpdateClientProfileResult(ClientProfileModel clientProfileModel) {
        this.clientProfileModel = clientProfileModel;
    }

    /**
     * Returns the clientProfileModel that was updated.
     * @return The updated clientProfileModel.
     */
    public ClientProfileModel getClientProfileModel() {
        return clientProfileModel;
    }

    @Override
    public String toString() {
        return "UpdateClientProfileResult{" +
                "clientProfileModel=" + clientProfileModel +
                '}';
    }
    /**
     * Builder class for constructing UpdateClientProfileResult objects.
     */
    public static class Builder{
        private ClientProfileModel clientProfileModel;

        public UpdateClientProfileResult.Builder withClientProfile(ClientProfileModel clientProfileModel){
            this.clientProfileModel = clientProfileModel;
            return this;
        }

        public UpdateClientProfileResult build(){
            return new UpdateClientProfileResult(clientProfileModel);
        }

    }

    public static UpdateServiceResult.Builder builder(){
        return new UpdateServiceResult.Builder();
    }

}
