package petproschedulerservice.activity.results;

import petproschedulerservice.dynamodb.Service;

import java.util.ArrayList;
import java.util.List;

public class GetServiceMenuResult {

    private final List<Service> allServicesList;

    private GetServiceMenuResult(List<Service> allServicesList) {
        this.allServicesList = allServicesList;
    }
    /**
     * Returns a list of all the Services in the DynamoDB table.
     *
     * @return A List object containing all the Services.
     */
    public List<Service> getAllServices() {
        return allServicesList;
    }
    /**
     * Returns a string representation of the GetServiceMenuResult object.
     *
     * @return A string containing the list of all Services.
     */
    @Override
    public String toString() {
        return "GetServiceMenuResult{" +
                "allServicesList=" + allServicesList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    /**
     * The Builder class for creating GetServiceMenuResult objects.
     */
    public static class Builder {
        private List<Service> allServicesList;

        public Builder withServicesList(List<Service> allServicesList) {

            this.allServicesList = new ArrayList<>(allServicesList);
            return this;
        }

        public GetServiceMenuResult build() {
            return new GetServiceMenuResult(allServicesList);
        }
    }
}
