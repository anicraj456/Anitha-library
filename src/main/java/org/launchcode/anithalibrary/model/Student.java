package org.launchcode.anithalibrary.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Student extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max=50, message = "Name must be between 3 and 50 characters")
    private String firstname;
    @Size(max = 500, message = "Description too long")
    private String lastname;

    @NotBlank (message = "Email is required")
    @Email(message = "Invalid email, try again")
    private String contactEmail;

    public Student(String firstname, String lastname, String contactEmail) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactEmail = contactEmail;
    }

    public Student () {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}