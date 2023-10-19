package petproschedulerservice.activity.results;

import petproschedulerservice.models.GroomerProfileModel;

public class DeleteGroomerProfileResult {
    private final GroomerProfileModel groomerProfileModel;
    /**
     * Constructs a new DeleteGroomerProfileResult object with the given Groomer Profile model.
     *
     * @param groomerProfileModel the Groomer Profile model deleted from the database
     */
    public DeleteGroomerProfileResult(GroomerProfileModel groomerProfileModel) {
        this.groomerProfileModel = groomerProfileModel;
    }
    /**
     * Returns the GroomerProfile model deleted from the database.
     *
     * @return the GroomerProfile model
     */
    public GroomerProfileModel getGroomerProfileModel() {
        return groomerProfileModel;
    }
    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "DeleteGroomerProfileResult{" +
                "groomerProfileModel=" + groomerProfileModel +
                '}';
    }

    public static class Builder{
        private GroomerProfileModel groomerProfileModel;

        public DeleteGroomerProfileResult.Builder withGroomerProfileModel(GroomerProfileModel groomerProfileModel){
            this.groomerProfileModel = groomerProfileModel;
            return this;
        }

        public DeleteGroomerProfileResult build(){
            return new DeleteGroomerProfileResult(groomerProfileModel);
        }

    }

    public static DeleteGroomerProfileResult.Builder builder(){
        return new DeleteGroomerProfileResult.Builder();
    }

}
