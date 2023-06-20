package petproschedulerservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeleteServiceRequest.Builder.class)
public class DeleteServiceRequest {
    private final String title;

    /**
     * Constructs a new DeleteServiceRequest with the given title.
     *
     * @param title the title of the Service to delete
     */
    public DeleteServiceRequest(String title) {
        this.title = title;
    }
    /**
     * Returns the title of the Service to delete.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns a string representation of this DeleteServiceRequest.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "DeleteServiceRequest{" +
                "title='" + title + '\'' +
                '}';
    }
    /**
     * Builder class for constructing DeleteServiceRequest objects.
     */
    @JsonPOJOBuilder
    public static class Builder{

        private String title;


        public DeleteServiceRequest.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public DeleteServiceRequest build(){
            return new DeleteServiceRequest(title);
        }

    }

    public static DeleteServiceRequest.Builder builder(){
        return new DeleteServiceRequest.Builder();
    }

}
