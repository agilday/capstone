package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import petproschedulerservice.dynamodb.Pet;

import java.util.List;

public class CreateProfileRequest {
    private final String id;
    private final String name;
    private final String phone;
    private final String address;
    private final List<String> notes;
    private final List<Pet> pets;

    private CreateProfileRequest(String id, String name, String phone, String address, List<String> notes, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.notes = notes;
        this.pets = pets;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getNotes() {
        return notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "CreateProfileRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", notes=" + notes +
                ", pets=" + pets +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static CreateProfileRequest.Builder builder() {
        return new CreateProfileRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String id;
        private String name;
        private String phone;
        private String address;
        private List<String> notes;
        private List<Pet> pets;

        public CreateProfileRequest.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public CreateProfileRequest.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public CreateProfileRequest.Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public CreateProfileRequest.Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public CreateProfileRequest.Builder withNotes(List<String> notes) {
            this.notes = notes;
            return this;
        }

        public CreateProfileRequest.Builder withPets(List<Pet> pets) {
            this.pets = pets;
            return this;
        }

        public CreateProfileRequest build() {
            return new CreateProfileRequest(id, name, phone, address, notes, pets);
        }
    }
}
