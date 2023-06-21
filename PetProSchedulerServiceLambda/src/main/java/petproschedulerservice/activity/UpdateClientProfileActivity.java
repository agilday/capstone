package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.UpdateClientProfileRequest;
import petproschedulerservice.activity.results.UpdateClientProfileResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.ClientProfileDao;
import petproschedulerservice.exceptions.InvalidAttributeValueException;
import petproschedulerservice.metrics.MetricsConstants;
import petproschedulerservice.metrics.MetricsPublisher;
import petproschedulerservice.models.ClientProfileModel;
import petproschedulerservice.utils.PetProUtils;

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

        if(!PetProUtils.isValidString(updateClientProfileRequest.getName())) {
            publishExceptionMetrics(true, false);
            throw new InvalidAttributeValueException("Client profile name [" + updateClientProfileRequest.getName() +
                    "] contains illegal characters.");
        }

        ClientProfile profile = clientProfileDao.getClientProfile(updateClientProfileRequest.getId());

        ClientProfile updateProfile = new ClientProfile();
                updateProfile.setId(updateClientProfileRequest.getId());
                updateProfile.setName(updateClientProfileRequest.getName()); 
                updateProfile.setPhone(updateClientProfileRequest.getPhone());
                updateProfile.setAddress(updateClientProfileRequest.getAddress());
                updateProfile.setNotes(updateClientProfileRequest.getNotes());
                updateProfile.setPets(updateClientProfileRequest.getPets());
                clientProfileDao.saveClientProfile(updateProfile);

        return UpdateClientProfileResult.builder()
                .withClientProfile(new ModelConverter().toClientProfileModel(updateProfile))
                .build();
    }
    /**
     * Helper method to publish exception metrics.
     * @param isInvalidAttributeValue indicates whether InvalidAttributeValueException is thrown
     * @param isInvalidAttributeChange indicates whether InvalidAttributeChangeException is thrown
     */
    private void publishExceptionMetrics(final boolean isInvalidAttributeValue,
                                         final boolean isInvalidAttributeChange) {
        metricsPublisher.addCount(MetricsConstants.UPDATECLIENTPROFILE_INVALIDATTRIBUTEVALUE_COUNT,
                isInvalidAttributeValue ? 1 : 0);
        metricsPublisher.addCount(MetricsConstants.UPDATECLIENTPROFILE_INVALIDATTRIBUTECHANGE_COUNT,
                isInvalidAttributeChange ? 1 : 0);
    }

}
