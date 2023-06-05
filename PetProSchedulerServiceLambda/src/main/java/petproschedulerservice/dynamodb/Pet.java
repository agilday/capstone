package petproschedulerservice.dynamodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pet {

    enum Status {
        LIVING,
        DECEASED
    }
    private String name;
    private String species;
    private String breed;
    private String vaccineExpr;
    private boolean aggression;
    private List<String> notes;
    private Status status;

    public Pet(String name, String species, String breed, String vaccineExpr, boolean aggression,
               List<String> notes, Status status) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.vaccineExpr = vaccineExpr;
        this.aggression = aggression;
        this.notes = new ArrayList<>();
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getVaccineExpr() {
        return vaccineExpr;
    }

    public void setVaccineExpr(String vaccineExpr) {
        this.vaccineExpr = vaccineExpr;
    }

    public Boolean getAggression() {
        return aggression;
    }

    public void setAggression(boolean aggression) {
        this.aggression = aggression;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void addNote(String note) {
        this.notes.add(note);
    }

    public void deleteNote(String note) {
        this.notes.remove(note);
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pet pet = (Pet) o;
        return name.equals(pet.name) &&
                species.equals(pet.species) &&
                breed.equals(pet.breed) &&
                vaccineExpr.equals(pet.vaccineExpr) &&
                aggression == pet.aggression &&
                notes.equals(pet.notes) &&
                status.equals(pet.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, species, breed, vaccineExpr, aggression, notes, status);
    }

}

