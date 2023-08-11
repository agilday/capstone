package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = CreateGroomerProfileRequest.Builder.class)
public class CreateGroomerProfileRequest {
    private final String lname;
    private final String fname;
    private final String phone;
    private final String availability;
    private final List<String> notes;
    private final Boolean groomsCats;

    private CreateGroomerProfileRequest(String lname, String fname, String phone, String availability, List<String> notes, Boolean groomsCats) {
        this.lname = lname;
        this.fname = fname;
        this.phone = phone;
        this.availability = availability;
        this.notes = notes;
        this.groomsCats = groomsCats;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
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
    public String toString() {
        return "CreateGroomerProfileRequest{" +
                ", lname='" + lname + '\'' +
                ", fname='" + fname + '\'' +
                ", phone='" + phone + '\'' +
                ", availability='" + availability + '\'' +
                ", notes='" + notes + '\'' +
                ", groomsCats='" + groomsCats + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String lname;
        private String fname;
        private String phone;
        private String availability;
        private List<String> notes;
        private Boolean groomsCats;


        public Builder withFname(String fname) {
            this.fname = fname;
            return this;
        }

        public Builder withLname(String lname) {
            this.lname = lname;
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

        public CreateGroomerProfileRequest build() {
            return new CreateGroomerProfileRequest(lname, fname, phone, availability, notes, groomsCats);
        }
    }
}

