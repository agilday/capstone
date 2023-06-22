import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.activity.GetClientProfileActivity;
import petproschedulerservice.activity.requests.GetClientProfileRequest;
import petproschedulerservice.activity.results.GetClientProfileResult;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.dynamodb.Pet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetClientProfileActivityTest {

    @Mock
    private ClientProfileDao profileDao;

    private GetClientProfileActivity getClientProfileActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
        getClientProfileActivity = new GetClientProfileActivity(profileDao);
    }

    @Test
    public void handleRequest_savedClientProfileIsFound_returnsClientProfileModelInResult() {
        //GIVEN
        String expectedId = "12345";
        String expectedName = "bill";
        String expectedPhone = "789-456-1230";
        String expectedAddress = "222 division st";
        List<String> expectedNotes = new ArrayList<>();
        List<String> expectedPets = new ArrayList<>();

        ClientProfile profile = new ClientProfile();
        profile.setId(expectedId);
        profile.setName(expectedName);
        profile.setPhone(expectedPhone);
        profile.setAddress(expectedAddress);
        profile.setNotes(expectedNotes);
        profile.setPets(expectedPets);

        when(profileDao.getClientProfile(expectedId)).thenReturn(profile);

        GetClientProfileRequest request = GetClientProfileRequest.builder()
                .withId(expectedId)
                .build();

        //WHEN
        GetClientProfileResult result = getClientProfileActivity.handleRequest(request);

        //THEN
        assertEquals(expectedId, result.getClientProfileModel().getId());
        assertEquals(expectedName, result.getClientProfileModel().getName());
        assertEquals(expectedPhone, result.getClientProfileModel().getPhone());
        assertEquals(expectedAddress, result.getClientProfileModel().getAddress());
        assertEquals(expectedNotes, result.getClientProfileModel().getNotes());
        assertEquals(expectedPets, result.getClientProfileModel().getPets());

    }

}
