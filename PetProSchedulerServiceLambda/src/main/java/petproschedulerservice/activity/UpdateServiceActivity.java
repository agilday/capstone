package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.UpdateServiceRequest;
import petproschedulerservice.activity.results.UpdateServiceResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.Service;
import petproschedulerservice.dynamodb.ServiceDao;
import petproschedulerservice.metrics.MetricsPublisher;
import petproschedulerservice.models.ServiceModel;

import javax.inject.Inject;

public class UpdateServiceActivity {

    private final Logger log = LogManager.getLogger();
    private final ServiceDao serviceDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdateServiceActivity object.
     *
     * @param serviceDao to access the services table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateServiceActivity(ServiceDao serviceDao, MetricsPublisher metricsPublisher) {
        this.serviceDao = serviceDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the event, updating it,
     * and persisting the Service.
     * <p>
     * It then returns the updated Service.
     * <p>
     * If the Service does not exist, this should throw a ServiceNotFoundException.
     * <p>
     * @param updateServiceRequest request object containing the Service name and description
     *                              associated with it
     * @return updateServiceResult result object containing the API defined {@link ServiceModel}
     */
    public UpdateServiceResult handleRequest(final UpdateServiceRequest updateServiceRequest) {
        log.info("Received UpdateServiceRequest {}", updateServiceRequest);

        Service service = serviceDao.getService(updateServiceRequest.getTitle());

        Service updateService = serviceDao.saveService(updateServiceRequest.getTitle(),
                updateServiceRequest.getDescription());

        return UpdateServiceResult.builder()
                .withService(new ModelConverter().toServiceModel(updateService))
                .build();
    }

}
