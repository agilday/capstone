package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

import java.util.List;
import java.util.Objects;

public class ClientProfile {
    private String id;
    private String name;
    private String phone;
    private List<String> notes;
    private List<Pet> pets;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // "name" is a reserved word in DDB, so the attribute in the table is called "playlistName".
    @DynamoDBAttribute(attributeName = "clientName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DynamoDBAttribute(attributeName = "notes")
    public List<String> getNotes() {
        return notes;
    }

    public void addNote(String note) {
        this.notes.add(note);
    }

    @DynamoDBAttribute(attributeName = "pets")
    public List<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
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
        ClientProfile profile = (ClientProfile) o;
        return id.equals(profile.id) &&
                name.equals(profile.name) &&
                phone.equals(profile.phone) &&
                notes.equals(profile.notes) &&
                pets.equals(profile.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, notes, pets);
    }

}
