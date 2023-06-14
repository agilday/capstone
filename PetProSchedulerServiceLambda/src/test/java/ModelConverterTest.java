import org.junit.jupiter.api.Test;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.Appointment;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.Service;
import petproschedulerservice.models.AppointmentModel;
import petproschedulerservice.models.ClientProfileModel;
import petproschedulerservice.models.ServiceModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {

    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toClientProfileModel_withNotes_convertsClientProfile() {
        //GIVEN
        ClientProfile profile = new ClientProfile();
        profile.setId("123");
        profile.setName("jill");
        profile.setPhone("789-456-1230");
        profile.setAddress("101 8th ave");
        profile.setNotes(List.of("alt pickup is John"));
        profile.setPets(new ArrayList<>());

        //WHEN + THEN
        ClientProfileModel profileModel = modelConverter.toClientProfileModel(profile);

        assertEquals(profile.getId(), profileModel.getId());
        assertEquals(profile.getName(), profileModel.getName());
        assertEquals(profile.getPhone(), profileModel.getPhone());
        assertEquals(profile.getAddress(), profileModel.getAddress());
        assertEquals(profile.getNotes(), profileModel.getNotes());
        assertEquals(profile.getPets(), profileModel.getPets());

    }

    @Test
    void toAppointmentModel_convertsAppointment() {
        //GIVEN
        Appointment app = new Appointment();
        app.setId("456");
        app.setClient("marjorie");
        app.setDateTime("05/10/24 10:00am");
        app.setPet("fluffy");
        app.setService("wash + blowdry");

        //WHEN + THEN
        AppointmentModel appointmentModel = modelConverter.toAppointmentModel(app);

        assertEquals(app.getId(), appointmentModel.getId());
        assertEquals(app.getClient(), appointmentModel.getClient());
        assertEquals(app.getDateTime(), appointmentModel.getDateTime());
        assertEquals(app.getPet(), appointmentModel.getPet());
        assertEquals(app.getService(), appointmentModel.getService());
    }

    @Test
    void toServiceModel_convertsToServiceModel() {
        //GIVEN
        Service service = new Service();
        service.setTitle("Wash and blowdry");
        service.setDescription("Thorough wash with shampoo + conditioner followed by a blow dry and pet-friendly cologne.");

        //WHEN + THEN
        ServiceModel serviceModel = modelConverter.toServiceModel(service);

        assertEquals(service.getTitle(), serviceModel.getTitle());
        assertEquals(service.getDescription(), serviceModel.getDescription());
    }
}
