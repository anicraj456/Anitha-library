package org.launchcode.anithalibrary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

 import java.io.Serializable;
@Embeddable
public class StudentBookId implements Serializable {

    @Column(name="bookId")
    private int studentId;

    @Column(name="studentId")
    private int bookId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
