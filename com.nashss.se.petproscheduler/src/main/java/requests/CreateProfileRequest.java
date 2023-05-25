package requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.List;

public class CreateProfileRequest {
    private final String id;
    private final String name;
    private final String phone;
    private final List<String> notes;
    private final List<String> pets;

    private CreateProfileRequest(String id, String name, String phone, List<String> notes, List<String> pets) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.notes = new ArrayList<>();
        this.pets = new ArrayList<>();
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

    public List<String> getNotes() {
        return notes;
    }

    public List<String> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "CreateProfileRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
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
        private List<String> notes;
        private List<String> pets;

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

        public CreateProfileRequest.Builder withNotes(List<String> notes) {
            this.notes = notes;
            return this;
        }

        public CreateProfileRequest.Builder withPets(List<String> pets) {
            this.pets = pets;
            return this;
        }

        public CreateProfileRequest build() {
            return new CreateProfileRequest(id, name, phone, notes, pets);
        }
    }
}
