package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.CreateServiceRequest;
import petproschedulerservice.activity.results.CreateServiceResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.Service;
import petproschedulerservice.dynamodb.ServiceDao;
import petproschedulerservice.models.ServiceModel;

import javax.inject.Inject;

public class CreateServiceActivity {

    private final Logger log = LogManager.getLogger();
    private final ServiceDao serviceDao;

    /**
     * Instantiates a new CreateServiceActivity object.
     *
     * @param serviceDao ServiceDao to access the servicemenu table.
     */
    @Inject
    public CreateServiceActivity(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    /**
     * This method handles the incoming request by persisting a new Service
     * <p>
     * It then returns the newly created Service.
     * <p>
     * @param createServiceRequest request object containing the Service title and description
     *                              associated with it
     * @return createServiceResult result object containing the API defined {@link ServiceModel}
     */
    public CreateServiceResult handleRequest(final CreateServiceRequest createServiceRequest) {
        log.info("Received CreateServiceRequest {}", createServiceRequest);

        Service newService = new Service();
        newService.setTitle(createServiceRequest.getTitle());
        newService.setDescription(createServiceRequest.getDescription());;

        serviceDao.saveService(newService.getTitle(), newService.getDescription());

        ServiceModel serviceModel = new ModelConverter().toServiceModel(newService);
        return CreateServiceResult.builder()
                .withService(serviceModel)
                .build();
    }
}
