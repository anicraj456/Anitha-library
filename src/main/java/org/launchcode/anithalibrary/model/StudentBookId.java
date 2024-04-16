package org.launchcode.anithalibrary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

 import java.io.Serializable;
@Embeddable
public class StudentBookId implements Serializable {

    @Column(name="Book_Id")
    private int studentId;

    @Column(name="Student_Id")
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
