import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.activity.UpdateClientProfileActivity;
import petproschedulerservice.activity.requests.UpdateClientProfileRequest;
import petproschedulerservice.activity.results.UpdateClientProfileResult;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.exceptions.InvalidAttributeException;
import petproschedulerservice.metrics.MetricsConstants;
import petproschedulerservice.metrics.MetricsPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateClientProfileActivityTest {

    @Mock
    private ClientProfileDao profileDao;

    @Mock
    private MetricsPublisher metricsPublisher;

    private UpdateClientProfileActivity updateClientProfileActivity;

    @BeforeEach
    public void setup() {
        openMocks(this);
        updateClientProfileActivity = new UpdateClientProfileActivity(profileDao, metricsPublisher);
    }

    @Test
    void handleRequest_goodRequest_updatesProfile() {
        //GIVEN
        String id = "id";
        String expectedName = "expectedName";

        UpdateClientProfileRequest request = UpdateClientProfileRequest.builder()
                .withId(id)
                .withName(expectedName)
                .build();

        ClientProfile profile = new ClientProfile();
        profile.setId(id);
        profile.setName(expectedName);

        when(profileDao.getClientProfile(id)).thenReturn(profile);
        when(profileDao.saveClientProfile(profile)).thenReturn(profile);

        //WHEN
        UpdateClientProfileResult result = updateClientProfileActivity.handleRequest(request);

        //THEN
        assertEquals(expectedName, result.getClientProfileModel().getName());
    }

    @Test
    void handleRequest_badRequest_throwsInvalidAttributeValueException() {
        //GIVEN
        UpdateClientProfileRequest request = UpdateClientProfileRequest.builder()
                .withId("id")
                .withName("I'm illegal")
                .build();

        //WHEN + THEN
        try {
            updateClientProfileActivity.handleRequest(request);
            fail("expected invalidattributevalueexception to be thrown");
        } catch (InvalidAttributeException e) {
            verify(metricsPublisher).addCount(MetricsConstants.UPDATECLIENTPROFILE_INVALIDATTRIBUTEVALUE_COUNT, 1);
            verify(metricsPublisher).addCount(MetricsConstants.UPDATECLIENTPROFILE_INVALIDATTRIBUTECHANGE_COUNT, 0);
        }
    }
}
