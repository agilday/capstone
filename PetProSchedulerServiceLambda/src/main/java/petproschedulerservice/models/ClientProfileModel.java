package petproschedulerservice.models;

import petproschedulerservice.dynamodb.Pet;

import java.util.List;
import java.util.Objects;

public class ClientProfileModel {
    private final String id;
    private final String name;
    private final String phone;
    private final String address;
    private final List<String> notes;
    private final List<String> pets;

    private ClientProfileModel(String id, String name, String phone, String address,
                             List<String> notes, List<String> pets) {
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

    public List<String> getPets() {
        return pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientProfileModel that = (ClientProfileModel) o;

        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(address, that.address) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(pets, that.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, address, notes, pets);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String phone;
        private String address;
        private List<String> notes;
        private List<String> pets;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

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

        public ClientProfileModel build() {
            return new ClientProfileModel(id, name, phone, address, notes, pets);
        }
    }

}
