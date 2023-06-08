package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.GetServiceMenuRequest;
import petproschedulerservice.activity.results.GetServiceMenuResult;
import petproschedulerservice.dynamodb.Service;
import petproschedulerservice.dynamodb.ServiceDao;

import javax.inject.Inject;
import java.util.List;

public class GetServiceMenuActivity {
    private final Logger log = LogManager.getLogger();
    private final ServiceDao serviceDao;

    @Inject
    public GetServiceMenuActivity(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
    /**
     * Handles a {@link GetServiceMenuRequest} and returns a {@link GetServiceMenuResult} containing a list of all services.
     *
     * @return a {@link GetServiceMenuResult} containing a list of all services
     */
    public GetServiceMenuResult handleRequest(){
        log.info("Receive GetServiceMenusRequest {} ", "called Get Service Menu");

        List<Service> listServices = serviceDao.getAllServices();

        return GetServiceMenuResult.builder()
                .withServicesList(listServices)
                .build();
    }
}
