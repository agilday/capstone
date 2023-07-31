package petproschedulerservice.models;

import java.util.List;
import java.util.Objects;

public class GroomerProfileModel {
    private final String lname;
    private final String fname;
    private final String phone;
    private final String availability;
    private final List<String> notes;
    private final Boolean groomsCats;

    private GroomerProfileModel(String lname, String fname, String phone, String availability,
                               List<String> notes, Boolean groomsCats) {
        this.lname = lname;
        this.fname = fname;
        this.phone = phone;
        this.availability = availability;
        this.notes = notes;
        this.groomsCats = groomsCats;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvailability() {
        return availability;
    }

    public List<String> getNotes() {
        return notes;
    }

    public Boolean getGroomsCats() {
        return groomsCats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GroomerProfileModel that = (GroomerProfileModel) o;

        return Objects.equals(lname, that.lname) && Objects.equals(fname, that.fname) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(availability, that.availability) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(groomsCats, that.groomsCats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lname, fname, phone, availability, notes, groomsCats);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String lname;
        private String fname;
        private String phone;
        private String availability;
        private List<String> notes;
        private Boolean groomsCats;

        public Builder withLname(String lname) {
            this.lname = lname;
            return this;
        }

        public Builder withFname(String fname) {
            this.fname = fname;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public Builder withNotes(List<String> notes) {
            this.notes = notes;
            return this;
        }

        public Builder withGroomsCats(Boolean groomsCats) {
            this.groomsCats = groomsCats;
            return this;
        }

        public GroomerProfileModel build() {
            return new GroomerProfileModel(lname, fname, phone, availability, notes, groomsCats);
        }
    }

}

