//package petproschedulerservice.activity.results;
//
//import petproschedulerservice.models.ClientProfileModel;
//
//public class CreateProfileResult {
//    private final ClientProfileModel profile;
//
//    private CreateProfileResult(ClientProfileModel profile) {
//        this.profile = profile;
//    }
//
//    public ClientProfileModel getProfile() {
//        return profile;
//    }
//
//    @Override
//    public String toString() {
//        return "CreateProfileResult{" +
//                "clientprofile=" + profile +
//                '}';
//    }
//
//    //CHECKSTYLE:OFF:Builder
//    public static CreateProfileResult.Builder builder() {
//        return new CreateProfileResult.Builder();
//    }
//
//    public static class Builder {
//        private ClientProfileModel profile;
//
//        public CreateProfileResult.Builder withProfile(ClientProfileModel profile) {
//            this.profile = profile;
//            return this;
//        }
//
//        public CreateProfileResult build() {
//            return new CreateProfileResult(profile);
//        }
//    }
//}
