package petproschedulerservice.activity.results;

import petproschedulerservice.models.ClientProfileModel;

public class CreateClientProfileResult {
    private final ClientProfileModel profile;

    private CreateClientProfileResult(ClientProfileModel profile) {
        this.profile = profile;
    }

    public ClientProfileModel getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "CreateProfileResult{" +
                "clientprofile=" + profile +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ClientProfileModel profile;

        public Builder withProfile(ClientProfileModel profile) {
            this.profile = profile;
            return this;
        }

        public CreateClientProfileResult build() {
            return new CreateClientProfileResult(profile);
        }
    }
}
