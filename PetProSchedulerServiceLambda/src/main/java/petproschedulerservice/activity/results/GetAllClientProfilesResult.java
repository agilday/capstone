package petproschedulerservice.activity.results;

import petproschedulerservice.dynamodb.ClientProfile;

import java.util.ArrayList;
import java.util.List;

public class GetAllClientProfilesResult {

    private final List<ClientProfile> allClientProfilesList;

    private GetAllClientProfilesResult(List<ClientProfile> allClientProfilesList) {
        this.allClientProfilesList = allClientProfilesList;
    }
    /**
     * Returns a list of all the events in the DynamoDB table.
     *
     * @return A List object containing all the ClientProfiles.
     */
    public List<ClientProfile> getAllClientProfilesList() {
        return allClientProfilesList;
    }
    /**
     * Returns a string representation of the GetAllClientProfilesResult object.
     *
     * @return A string containing the list of all ClientProfiles.
     */
    @Override
    public String toString() {
        return "GetAllClientProfilesResult{" +
                "allClientProfilesList=" + allClientProfilesList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    /**
     * The Builder class for creating GetAllClientProfilesResult objects.
     */
    public static class Builder {
        private List<ClientProfile> allClientProfilesList;

        public Builder withClientProfilesList(List<ClientProfile> allClientProfilesList) {

            this.allClientProfilesList = new ArrayList<>(allClientProfilesList);
            return this;
        }

        public GetAllClientProfilesResult build() {
            return new GetAllClientProfilesResult(allClientProfilesList);
        }
    }
}
