package org.launchcode.anithalibrary.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class Book extends AbstractEntity{
    @Size(max = 500, message = "Description too long!")
    private String description;

    private String authorName;

    private int publishingYear;

    private double price;

    private String genre;

    private int copies;

    public Book() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}