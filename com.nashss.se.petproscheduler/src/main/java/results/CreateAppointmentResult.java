package results;

import models.AppointmentModel;

public class CreateAppointmentResult {
    private final AppointmentModel appointment;

    private CreateAppointmentResult(AppointmentModel appointment) {
        this.appointment = appointment;
    }

    public AppointmentModel getAppointment() {
        return appointment;
    }

    @Override
    public String toString() {
        return "CreateAppointmentResult{" +
                "appointment=" + appointment +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AppointmentModel appointment;

        public Builder withAppointment(AppointmentModel appointment) {
            this.appointment = appointment;
            return this;
        }

        public CreateAppointmentResult build() {
            return new CreateAppointmentResult(appointment);
        }
    }
}
