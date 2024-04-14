package org.launchcode.anithalibrary.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Bookinfo extends AbstractEntity {
    @NotBlank(message = "Book Name is required")
    @Size(min = 3, max=50, message = "Book Name must be between 3 and 50 characters")
    private String bookname;
    @Size(min = 3, max = 500, message = "Author Name must be between 3 and 50 characters")
    private String authorname;

    @NotBlank (message = "Genre is required")
    private String genre;

    public Bookinfo (String bookname, String authorname, String genre) {
        this.bookname = bookname;
        this.authorname = authorname;
        this.genre = genre;
    }
    public Bookinfo (){
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookname='" + bookname + '\'' +
                ", authorname='" + authorname + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}