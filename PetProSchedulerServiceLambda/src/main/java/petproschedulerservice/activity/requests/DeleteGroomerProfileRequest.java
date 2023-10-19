package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeleteGroomerProfileRequest.Builder.class)
public class DeleteGroomerProfileRequest {

    private final String lname;
    private final String fname;

    /**
     * Constructs a new DeleteGroomerProfileRequest with the given ID.
     *
     * @param lname the last name of the Groomer Profile to delete
     * @param fname the first name of the Groomer Profile to delete
     */
    public DeleteGroomerProfileRequest(String lname, String fname) {
        this.lname = lname;
        this.fname = fname;
    }
    /**
     * Returns the last name of the Groomer Profile to delete.
     *
     * @return lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * Returns the first name of the Groomer Profile to delete.
     *
     * @return fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * Returns a string representation of this DeleteGroomerProfileRequest.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "DeleteGroomerProfileRequest{" +
                "lname='" + lname + '\'' +
                "fname='" + fname + '\'' +
                '}';
    }
    /**
     * Builder class for constructing DeleteGroomerProfileRequest objects.
     */
    @JsonPOJOBuilder
    public static class Builder{

        private String lname;
        private String fname;


        public DeleteGroomerProfileRequest.Builder withLname(String lname) {
            this.lname = lname;
            return this;
        }

        public DeleteGroomerProfileRequest.Builder withFname(String fname) {
            this.fname = fname;
            return this;
        }

        public DeleteGroomerProfileRequest build(){
            return new DeleteGroomerProfileRequest(lname, fname);
        }

    }

    public static DeleteGroomerProfileRequest.Builder builder(){
        return new DeleteGroomerProfileRequest.Builder();
    }
}

