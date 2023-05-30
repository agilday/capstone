package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Appointment {
    private String id;
    private String client;
    private String dateTime;
    private Pet pet;
    private String service;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // "name" is a reserved word in DDB, so the attribute in the table is called "playlistName".
    @DynamoDBAttribute(attributeName = "client")
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @DynamoDBRangeKey(attributeName = "dateTime")
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @DynamoDBAttribute(attributeName = "pet")
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @DynamoDBAttribute(attributeName = "service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Appointment appointment = (Appointment) o;
        return id.equals(appointment.id) &&
                dateTime.equals(appointment.dateTime) &&
                client.equals(appointment.client) &&
                pet.equals(appointment.pet) &&
                service.equals(appointment.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, client, pet, service);
    }

}
