import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import petproschedulerservice.dynamodb.*;
import petproschedulerservice.metrics.MetricsPublisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClientProfileDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;
    private ClientProfileDao target;

    @BeforeEach
    public void setup() {
        initMocks(this);
        target = new ClientProfileDao(dynamoDBMapper, metricsPublisher);
    }


    @Test
    void saveClientProfile_givenClientProfileToSave_savesClientProfile() {
        //GIVEN
        List<String> pets = new ArrayList<>();
        Pet scotch = new Pet("Scotch", "feline", "bengal", "aug 2023", false, new ArrayList<>(), Pet.Status.LIVING);
        pets.add("scotch");
        ClientProfile ashley = new ClientProfile();
        ashley.setId("123");
        ashley.setName("ashley");
        ashley.setPhone("000-000-0000");
        ashley.setAddress("123 broadway");
        ashley.setNotes(Collections.singletonList("alt pickup matt"));
        ashley.setPets(pets);

        //WHEN
        target.saveClientProfile(ashley);

        //THEN
        verify(dynamoDBMapper).save(ashley);

    }

    @Test
    void getClientProfile_givenValidClientProfile_returnsClientProfile() {
        //GIVEN
        String id = "12345";

        when(dynamoDBMapper.load(ClientProfile.class, id)).thenReturn(new ClientProfile());

        //WHEN
        ClientProfile profile = target.getClientProfile(id);

        //THEN
        assertNotNull(profile);
        verify(dynamoDBMapper).load(ClientProfile.class, id);

    }

}
