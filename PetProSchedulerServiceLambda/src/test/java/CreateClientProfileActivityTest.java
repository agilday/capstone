import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.activity.CreateClientProfileActivity;
import petproschedulerservice.activity.requests.CreateClientProfileRequest;
import petproschedulerservice.activity.results.CreateClientProfileResult;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.dynamodb.Pet;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateClientProfileActivityTest {

    @Mock
    private ClientProfileDao profileDao;

    private CreateClientProfileActivity createClientProfileActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createClientProfileActivity = new CreateClientProfileActivity(profileDao);
    }

    @Test
    public void handleRequest_validName_createsAndSavesCLientProfile() throws InvalidAttributeValueException {
        //GIVEN
        String expectedName = "alex";
        String expectedPhone = "123-456-7890";
        String expectedAddress = "123 demonbreun";
        List<String> notes = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();

        CreateClientProfileRequest request = CreateClientProfileRequest.builder()
                .withName(expectedName)
                .withPhone(expectedPhone)
                .withAddress(expectedAddress)
                .withNotes(notes)
                .withPets(pets)
                .build();

        //WHEN
        CreateClientProfileResult result = createClientProfileActivity.handleRequest(request);

        //THEN
        verify(profileDao).saveClientProfile(result.getProfile().getId(), result.getProfile().getName(),
                result.getProfile().getPhone(), result.getProfile().getAddress(), result.getProfile().getNotes(), result.getProfile().getPets());

        assertNotNull(result.getProfile().getId());
        assertEquals(expectedName, result.getProfile().getName());
        assertEquals(expectedPhone, result.getProfile().getPhone());
        assertEquals(expectedAddress, result.getProfile().getAddress());
        assertEquals(notes, result.getProfile().getNotes());
        assertEquals(pets, result.getProfile().getPets());

    }
    @Test
    public void handleRequest_invalidName_createsAndSavesCLientProfile() throws InvalidAttributeValueException {
        //GIVEN
        String expectedName = "i'm illegal";
        String expectedPhone = "123-456-7890";
        String expectedAddress = "123 demonbreun";
        List<String> notes = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();

        CreateClientProfileRequest request = CreateClientProfileRequest.builder()
                .withName(expectedName)
                .withPhone(expectedPhone)
                .withAddress(expectedAddress)
                .withNotes(notes)
                .withPets(pets)
                .build();

        //WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createClientProfileActivity.handleRequest(request));

    }
}
