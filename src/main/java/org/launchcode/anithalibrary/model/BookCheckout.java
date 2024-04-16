package org.launchcode.anithalibrary.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class BookCheckout {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    //private String  bookName;
    //@ManyToOne
    //@JoinColumn(name="student_id")
    private int studentId;

    //private String studentName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedReturnDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualReturnDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date heldUntilDate;

    private boolean isCheckout;

    private boolean isHold;

    public BookCheckout() {
    }

    public int getId() {
        return id;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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

    public boolean isCheckout() {
        return isCheckout;
    }

    public void setCheckout(boolean checkout) {
        isCheckout = checkout;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getHeldUntilDate() {
        return heldUntilDate;
    }

    public void setHeldUntilDate(Date heldUntilDate) {
        this.heldUntilDate = heldUntilDate;
    }

    public boolean isHold() {
        return isHold;
    }

    public void setHold(boolean isHold) {
        this.isHold = isHold;
    }
}

/* teena new bookcheckout
package org.launchcode.library.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class BookCheckout {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    //private String  bookName;
    //@ManyToOne
    //@JoinColumn(name="student_id")
    private int studentId;

    //private String studentName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedReturnDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualReturnDate;

    private boolean isCheckout;

    public BookCheckout() {
    }

    public int getId() {
        return id;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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

    public boolean isCheckout() {
        return isCheckout;
    }

    public void setCheckout(boolean checkout) {
        isCheckout = checkout;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
 */