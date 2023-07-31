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
public class GroomerProfileDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a ClientProfileDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the profiles table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public GroomerProfileDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link GroomerProfile} corresponding to the specified lname.
     *
     * @param lname the Profile last name
     * @return the stored GroomerProfile, or null if none was found.
     */
    public GroomerProfile getGroomerProfile(String lname) {
        GroomerProfile profile = this.dynamoDbMapper.load(GroomerProfile.class, lname);

        if (profile == null) {
            metricsPublisher.addCount(MetricsConstants.GETPROFILE_PROFILENOTFOUND_COUNT, 1);
            throw new ProfileNotFoundException("Could not find profile with lname " + lname);
        }
        metricsPublisher.addCount(MetricsConstants.GETPROFILE_PROFILENOTFOUND_COUNT, 0);
        return profile;
    }

    public List<GroomerProfile> getAllGroomerProfiles() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<GroomerProfile> groomerProfilesList = dynamoDbMapper.scan(GroomerProfile.class, scanExpression);
        return groomerProfilesList;
    }

    /**
     * Saves (creates or updates) the given profile.
     *
     * @param GroomerProfile profile to save
     * @return The GroomerProfile object that was saved
     */
    public void saveGroomerProfile(GroomerProfile profile) {
        this.dynamoDbMapper.save(profile);
    }

    /**
     * Perform a search (via a "scan") of the profiles table for profiles matching the given criteria.
     *
     * Both "profileName" and "tags" attributes are searched.
     * The criteria are an array of Strings. Each element of the array is search individually.
     * ALL elements of the criteria array must appear in the profileName or the tags (or both).
     * Searches are CASE SENSITIVE.
     *
     * @param criteria an array of String containing search criteria.
     * @return a List of ClientProfile objects that match the search criteria.
     */
    public List<GroomerProfile> searchGroomerProfile(String[] criteria) {
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

        return this.dynamoDbMapper.scan(GroomerProfile.class, dynamoDBScanExpression);
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

