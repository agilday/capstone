package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.DeleteServiceRequest;
import petproschedulerservice.activity.results.DeleteServiceResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.Service;
import petproschedulerservice.dynamodb.ServiceDao;
import petproschedulerservice.models.ServiceModel;

import javax.inject.Inject;

public class DeleteServiceActivity {
    private final Logger log = LogManager.getLogger();
    private final ServiceDao serviceDao;

    @Inject
    public DeleteServiceActivity(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
    /**
     * Handles a request for deleting a service by its title.
     *
     * @param deleteServiceRequest the request object containing the service title.
     * @return a {@code DeleteServiceResult} object containing the deleted service.
     */
    public DeleteServiceResult handleRequest(final DeleteServiceRequest deleteServiceRequest){
        log.info("Receive DeleteServiceResult {} ", deleteServiceRequest);


        String title = deleteServiceRequest.getTitle();
        serviceDao.deleteService(title);

        Service service = new Service();
        service.setTitle(title);
        ServiceModel serviceModel = new ModelConverter().toServiceModel(service);

        return DeleteServiceResult.builder()
                .withServiceModel(serviceModel)
                .build();
    }
}
