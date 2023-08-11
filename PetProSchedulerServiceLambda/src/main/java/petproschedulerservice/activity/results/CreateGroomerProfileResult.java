package petproschedulerservice.activity.results;

import petproschedulerservice.models.GroomerProfileModel;

public class CreateGroomerProfileResult {
    private final GroomerProfileModel profile;

    private CreateGroomerProfileResult(GroomerProfileModel profile) {
        this.profile = profile;
    }

    public GroomerProfileModel getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "CreateGroomerProfileResult{" +
                "groomerprofile=" + profile +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static CreateGroomerProfileResult.Builder builder() {
        return new CreateGroomerProfileResult.Builder();
    }

    public static class Builder {
        private GroomerProfileModel profile;

        public CreateGroomerProfileResult.Builder withProfile(GroomerProfileModel profile) {
            this.profile = profile;
            return this;
        }

        public CreateGroomerProfileResult build() {
            return new CreateGroomerProfileResult(profile);
        }
    }
}
