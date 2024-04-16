package org.launchcode.anithalibrary.model;

import java.util.ArrayList;

public class StudentData {
    /**
     * Returns the results of searching the book data by field and search term.
     * @param column Book field that should be searched.
     * @param value Value of the field to search for.
     * @param allStudents The list of books to search.
     * @return List of all books matching the criteria.
     */
    public static ArrayList<Student> findByColumnAndValue(String column, String value, Iterable<Student> allStudents) {

        ArrayList<Student> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Student>) allStudents;
        }

        if (column.equals("all")){
            results = findByValue(value, allStudents);
            return results;
        }
        for (Student student : allStudents) {

            String aValue = getFieldValue(student, column);
            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(student);
            }
        }
        return results;
    }

    public static String getFieldValue(Student student, String fieldName) {
        String theValue="";
        if (fieldName.equals("lastname")) {
            theValue = student.getLastname();
        } else if (fieldName.equals("email")) {
            theValue = student.getContactEmail();
        }
            return theValue;
    }

    /**
     * Search all Book fields for the given term.
     *
     * @param value The search term to look for.
     * @param allStudents The list of books to search.
     * @return  List of all books with at least one field containing the value.
     */
    public static ArrayList<Student> findByValue(String value, Iterable<Student> allStudents) {

        ArrayList<Student> results = new ArrayList<>();

        for (Student student : allStudents) {

            if (student.getLastname().toLowerCase().contains(value.toLowerCase())) {
                results.add(student);
            } else if (null !=student.getContactEmail() && student.getContactEmail().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(student);
            }
        }
        return results;
    }

}
