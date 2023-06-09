package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import petproschedulerservice.dynamodb.Pet;

import java.util.List;

@JsonDeserialize(builder = UpdateClientProfileRequest.Builder.class)
public class UpdateClientProfileRequest {
    private final String id;
    private final String name;
    private final String phone;
    private final String address;
    private final List<String> notes;
    private final List<Pet> pets;

    private UpdateClientProfileRequest(String id, String name, String phone, String address,
                                       List<String> notes, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.notes = notes;
        this.pets = pets;
    }

    /**
     * Returns the id of the client profile.
     *
     * @return The id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the client.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the phone number of the client.
     *
     * @return The phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the address of the client.
     *
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the notes of the client.
     *
     * @return The notes.
     */
    public List<String> getNotes() {
        return notes;
    }

    /**
     * Returns the pets of the client.
     *
     * @return The pets.
     */
    public List<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "UpdateClientProfileRequest{" +
                "id='" + id + '\'' +
                "name='" + name +
                "phone='" + phone +
                "address='" + address +
                "notes='" + notes +
                "pets='" + pets +
                '}';
    }

    public static UpdateClientProfileRequest.Builder builder() {
        return new UpdateClientProfileRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String id;
        private String name;
        private String phone;
        private String address;
        private List<String> notes;
        private List<Pet> pets;

        public UpdateClientProfileRequest.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public UpdateClientProfileRequest.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public UpdateClientProfileRequest.Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UpdateClientProfileRequest.Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public UpdateClientProfileRequest.Builder withNotes(List<String> notes) {
            this.notes = notes;
            return this;
        }

        public UpdateClientProfileRequest.Builder withPets(List<Pet> pets) {
            this.pets = pets;
            return this;
        }

        public UpdateClientProfileRequest build() {
            return new UpdateClientProfileRequest(id, name, phone,
                    address, notes, pets);
        }
    }
}
