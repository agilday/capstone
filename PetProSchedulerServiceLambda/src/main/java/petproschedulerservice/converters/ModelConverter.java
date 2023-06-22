package petproschedulerservice.converters;

import petproschedulerservice.dynamodb.Appointment;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.Pet;
import petproschedulerservice.dynamodb.Service;
import petproschedulerservice.models.AppointmentModel;
import petproschedulerservice.models.ClientProfileModel;
import petproschedulerservice.models.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link ClientProfile} into a {@link ClientProfileModel} representation.
     *
     * @param profile the profile to convert
     * @return the converted profile
     */
    public ClientProfileModel toClientProfileModel(ClientProfile profile) {
        List<String> pets = null;
        if (profile.getPets() != null) {
            pets = new ArrayList<>(profile.getPets());
        }
        List<String> notes = null;
        if (profile.getNotes() != null) {
            notes = new ArrayList<>(profile.getNotes());
        }

        return ClientProfileModel.builder()
                .withId(profile.getId())
                .withName(profile.getName())
                .withPhone(profile.getPhone())
                .withAddress(profile.getAddress())
                .withNotes(notes)
                .withPets(pets)
                .build();
    }

    /**
     * Converts a provided Appointment into a AppointmentModel representation.
     *
     * @param appointment the Appointment to convert to AppointmentModel
     * @return the converted AppointmentModel with fields mapped from appointment
     */
    public AppointmentModel toAppointmentModel(Appointment appointment) {
        return AppointmentModel.builder()
                .withId(appointment.getId())
                .withClient(appointment.getClient())
                .withDateTime(appointment.getDateTime())
                .withPet(appointment.getPet())
                .withService(appointment.getService())
                .build();
    }

    public ServiceModel toServiceModel(Service service) {
        return ServiceModel.builder()
                .withTitle(service.getTitle())
                .withDescription(service.getDescription())
                .build();
    }

}
