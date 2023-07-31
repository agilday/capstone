package petproschedulerservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName="groomerprofiles")


public class GroomerProfile {
    private String lname;
    private String fname;
    private String phone;
    private String availability;
    private List<String> notes;
    private Boolean groomsCats;

    @DynamoDBHashKey(attributeName = "lname")
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @DynamoDBRangeKey(attributeName = "fname")
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @DynamoDBAttribute(attributeName = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DynamoDBAttribute(attributeName = "availability")
    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @DynamoDBAttribute(attributeName = "notes")
    public List<String> getNotes() {
        return notes;
    }

    public void addNote(String note) {
        this.notes.add(note);
    }

    @DynamoDBAttribute(attributeName = "groomsCats")
    public Boolean getGroomsCats() {
        return groomsCats;
    }

    public void setGroomsCats(Boolean groomsCats) {
        this.groomsCats = groomsCats;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroomerProfile profile = (GroomerProfile) o;
        return lname.equals(profile.lname) &&
                fname.equals(profile.fname) &&
                phone.equals(profile.phone) &&
                availability.equals(profile.availability) &&
                notes.equals(profile.notes) &&
                groomsCats.equals(profile.groomsCats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lname, fname, phone, availability, notes, groomsCats);
    }

}
