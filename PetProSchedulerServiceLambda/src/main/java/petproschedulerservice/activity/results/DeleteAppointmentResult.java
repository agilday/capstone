package petproschedulerservice.activity.results;

import petproschedulerservice.models.AppointmentModel;

public class DeleteAppointmentResult {

    private final AppointmentModel appointmentModel;
    /**
     * Constructs a new DeleteAppointmentResult object with the given appointment model.
     *
     * @param appointmentModel the appointment model deleted from the database
     */
    public DeleteAppointmentResult(AppointmentModel appointmentModel) {
        this.appointmentModel = appointmentModel;
    }
    /**
     * Returns the appointment model deleted from the database.
     *
     * @return the appointment model
     */
    public AppointmentModel getAppointmentModel() {
        return appointmentModel;
    }
    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "DeleteAppointmentResult{" +
                "appointmentModel=" + appointmentModel +
                '}';
    }

    public static class Builder{
        private AppointmentModel appointmentModel;

        public DeleteAppointmentResult.Builder withAppointmentModel(AppointmentModel appointmentModel){
            this.appointmentModel = appointmentModel;
            return this;
        }

        public DeleteAppointmentResult build(){
            return new DeleteAppointmentResult(appointmentModel);
        }

    }

    public static DeleteAppointmentResult.Builder builder(){
        return new DeleteAppointmentResult.Builder();
    }

}
