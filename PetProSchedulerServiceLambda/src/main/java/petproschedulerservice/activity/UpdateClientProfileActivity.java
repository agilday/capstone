package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.UpdateClientProfileRequest;
import petproschedulerservice.activity.results.UpdateClientProfileResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.metrics.MetricsPublisher;
import petproschedulerservice.models.ClientProfileModel;

import javax.inject.Inject;

public class UpdateClientProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final ClientProfileDao clientProfileDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdateClientProfileActivity object.
     *
     * @param clientProfileDao to access the profiles table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateClientProfileActivity(ClientProfileDao clientProfileDao, MetricsPublisher metricsPublisher) {
        this.clientProfileDao = clientProfileDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the ClientProfile, updating it,
     * and persisting the ClientProfile.
     * <p>
     * It then returns the updated ClientProfile.
     * <p>
     * If the Service does not exist, this should throw a ClientProfileNotFoundException.
     * <p>
     * @param updateClientProfileRequest request object containing the Service name and description
     *                              associated with it
     * @return updateClientProfileResult result object containing the API defined {@link ClientProfileModel}
     */
    public UpdateClientProfileResult handleRequest(final UpdateClientProfileRequest updateClientProfileRequest) {
        log.info("Received UpdateClientProfileRequest {}", updateClientProfileRequest);

        ClientProfile profile = clientProfileDao.getClientProfile(updateClientProfileRequest.getId());

        ClientProfile updateProfile = clientProfileDao.saveClientProfile(updateClientProfileRequest.getId(),
                updateClientProfileRequest.getName(), updateClientProfileRequest.getPhone(), updateClientProfileRequest.getAddress(),
                updateClientProfileRequest.getNotes(), updateClientProfileRequest.getPets());

        return UpdateClientProfileResult.builder()
                .withClientProfile(new ModelConverter().toClientProfileModel(updateProfile))
                .build();
    }

}
