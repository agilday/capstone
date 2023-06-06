package petproschedulerservice.activity.results;

import petproschedulerservice.dynamodb.Appointment;

import java.util.ArrayList;
import java.util.List;

public class GetAllAppointmentsResult {

    private final List<Appointment> allAppointmentsList;

    private GetAllAppointmentsResult(List<Appointment> allAppointmentsList) {
        this.allAppointmentsList = allAppointmentsList;
    }
    /**
     * Returns a list of all the events in the DynamoDB table.
     *
     * @return A List object containing all the Appointments.
     */
    public List<Appointment> getAllAppointmentsList() {
        return allAppointmentsList;
    }
    /**
     * Returns a string representation of the GetAllAppointmentsResult object.
     *
     * @return A string containing the list of all Appointments.
     */
    @Override
    public String toString() {
        return "GetAllAppointmentsResult{" +
                "allAppointmentsList=" + allAppointmentsList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    /**
     * The Builder class for creating GetAllAppointmentsResult objects.
     */
    public static class Builder {
        private List<Appointment> allAppointmentsList;

        public Builder withAppointmentsList(List<Appointment> allAppointmentsList) {

            this.allAppointmentsList = new ArrayList<>(allAppointmentsList);
            return this;
        }

        public GetAllAppointmentsResult build() {
            return new GetAllAppointmentsResult(allAppointmentsList);
        }
    }
}
