package petproschedulerservice.activity.results;

import petproschedulerservice.models.ClientProfileModel;

public class GetClientProfileResult {

    private final ClientProfileModel profileModel;
    /**
     * Constructs a new GetClientProfileResult object with the given profile model.
     *
     * @param profileModel the profile model retrieved from the database
     */
    public GetClientProfileResult(ClientProfileModel profileModel) {
        this.profileModel = profileModel;
    }
    /**
     * Returns the profile model retrieved from the database.
     *
     * @return the profile model
     */
    public ClientProfileModel getClientProfileModel() {
        return profileModel;
    }
    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "GetClientProfileResult{" +
                "profileModel=" + profileModel +
                '}';
    }

    public static class Builder{
        private ClientProfileModel profileModel;

        public Builder withClientProfileModel(ClientProfileModel profileModel){
            this.profileModel = profileModel;
            return this;
        }

        public GetClientProfileResult build(){
            return new GetClientProfileResult(profileModel);
        }

    }

    public static Builder builder(){
        return new Builder();
    }

}
