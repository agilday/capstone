package petproschedulerservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import petproschedulerservice.exceptions.ProfileNotFoundException;
import petproschedulerservice.metrics.MetricsConstants;
import petproschedulerservice.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ServiceDao {

    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a ServiceDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the services table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public ServiceDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link Service} corresponding to the specified title.
     *
     * @param title the Service title
     * @return the stored Service, or null if none was found.
     */
    public Service getService(String title) {
        Service service = this.dynamoDbMapper.load(Service.class, title);
        return service;
    }

    public List<Service> getAllServices() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Service> servicesList = dynamoDbMapper.scan(Service.class, scanExpression);
        return servicesList;
    }

        /**
     * Saves (creates or updates) the given service.
     *
     * @param Service service to save
     * @return The Service object that was saved
     */
    public void saveService(Service service) {
        this.dynamoDbMapper.save(service);
    }

    /**
     * Deletes (creates or updates) the given service.
     *
     * @param service The service to delete
     * @return The Service object that was deleted
     */
    public void deleteService(String title) {
        Service service = new Service();
        service.setTitle(title);
        this.dynamoDbMapper.delete(service);
    }


    /**
     * Perform a search (via a "scan") of the services table for services matching the given criteria.
     *
     * Both "title" and "tags" attributes are searched.
     * The criteria are an array of Strings. Each element of the array is search individually.
     * ALL elements of the criteria array must appear in the profileName or the tags (or both).
     * Searches are CASE SENSITIVE.
     *
     * @param criteria an array of String containing search criteria.
     * @return a List of Service objects that match the search criteria.
     */
    public List<Service> searchService(String[] criteria) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();

        if (criteria.length > 0) {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            String valueMapNamePrefix = ":c";

            StringBuilder nameFilterExpression = new StringBuilder();
            StringBuilder tagsFilterExpression = new StringBuilder();

            for (int i = 0; i < criteria.length; i++) {
                valueMap.put(valueMapNamePrefix + i,
                        new AttributeValue().withS(criteria[i]));
                nameFilterExpression.append(
                        filterExpressionPart("profileName", valueMapNamePrefix, i));
                tagsFilterExpression.append(
                        filterExpressionPart("tags", valueMapNamePrefix, i));
            }

            dynamoDBScanExpression.setExpressionAttributeValues(valueMap);
            dynamoDBScanExpression.setFilterExpression(
                    "(" + nameFilterExpression + ") or (" + tagsFilterExpression + ")");
        }

        return this.dynamoDbMapper.scan(Service.class, dynamoDBScanExpression);
    }

    private StringBuilder filterExpressionPart(String target, String valueMapNamePrefix, int position) {
        String possiblyAnd = position == 0 ? "" : "and ";
        return new StringBuilder()
                .append(possiblyAnd)
                .append("contains(")
                .append(target)
                .append(", ")
                .append(valueMapNamePrefix).append(position)
                .append(") ");
    }
}
