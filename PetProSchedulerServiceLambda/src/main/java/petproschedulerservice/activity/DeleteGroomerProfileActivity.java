package petproschedulerservice.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petproschedulerservice.activity.requests.DeleteGroomerProfileRequest;
import petproschedulerservice.activity.results.DeleteGroomerProfileResult;
import petproschedulerservice.converters.ModelConverter;
import petproschedulerservice.dynamodb.GroomerProfile;
import petproschedulerservice.dynamodb.GroomerProfileDao;
import petproschedulerservice.models.GroomerProfileModel;

import javax.inject.Inject;

public class DeleteGroomerProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final GroomerProfileDao groomerProfileDao;

    @Inject
    public DeleteGroomerProfileActivity(GroomerProfileDao groomerProfileDao) {
        this.groomerProfileDao = groomerProfileDao;
    }
    /**
     * Handles a request for deleting a groomerProfile by its title.
     *
     * @param deleteGroomerProfileRequest the request object containing the service title.
     * @return a {@code DeleteGroomerProfileResult} object containing the deleted service.
     */
    public DeleteGroomerProfileResult handleRequest(final DeleteGroomerProfileRequest deleteGroomerProfileRequest){
        log.info("Receive DeleteGroomerProfileResult {} ", deleteGroomerProfileRequest);


        String lname = deleteGroomerProfileRequest.getLname();
        groomerProfileDao.deleteGroomerProfile(lname);

        GroomerProfile groomerProfile = new GroomerProfile();
        groomerProfile.setLname(lname);
        GroomerProfileModel groomerProfileModel = new ModelConverter().toGroomerProfileModel(groomerProfile);

        return DeleteGroomerProfileResult.builder()
                .withGroomerProfileModel(groomerProfileModel)
                .build();
    }
}
