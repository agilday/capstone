package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = CreateClientProfileRequest.Builder.class)
public class CreateClientProfileRequest {
    private final String name;
    private final String phone;
    private final String address;
    private final List<String> notes;
    private final List<String> pets;

    private CreateClientProfileRequest(String name, String phone, String address, List<String> notes, List<String> pets) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.notes = notes;
        this.pets = pets;
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

    public List<String> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "CreateClientProfileRequest{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", notes='" + notes + '\'' +
                ", pets='" + pets + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String phone;
        private String address;
        private List<String> notes;
        private List<String> pets;


        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withNotes(List<String> notes) {
            this.notes = notes;
            return this;
        }

        public Builder withPets(List<String> pets) {
            this.pets = pets;
            return this;
        }

        public CreateClientProfileRequest build() {
            return new CreateClientProfileRequest(name, phone, address, notes, pets);
        }
    }
}
