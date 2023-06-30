package de.neuefische.mucjava231javafxstudents.model;

public record StudentWithoutId(String firstName, String lastName, String email, String courseOfStudies) {

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + courseOfStudies;
    }
}
