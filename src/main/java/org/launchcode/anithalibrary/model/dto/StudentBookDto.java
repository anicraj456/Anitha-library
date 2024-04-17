package org.launchcode.anithalibrary.model.dto;

import org.launchcode.anithalibrary.model.Student;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class StudentBookDto {
    private Integer bookId;

    private String bookName;
    private Integer studentId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedReturnDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualReturnDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date heldUntilDate;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Date getHeldUntilDate() {
        return heldUntilDate;
    }

    public void setHeldUntilDate(Date heldUntilDate) {
        this.heldUntilDate = heldUntilDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
