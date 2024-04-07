package org.launchcode.anithalibrary.model;

import java.util.ArrayList;

public class BookData {
    /**
     * Returns the results of searching the book data by field and search term.
     * @param column Book field that should be searched.
     * @param value Value of the field to search for.
     * @param allBooks The list of books to search.
     * @return List of all books matching the criteria.
     */
    public static ArrayList<Book> findByColumnAndValue(String column, String value, Iterable<Book> allBooks) {

        ArrayList<Book> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Book>) allBooks;
        }

        if (column.equals("all")){
            results = findByValue(value, allBooks);
            return results;
        }
        for (Book book : allBooks) {

            String aValue = getFieldValue(book, column);
            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public static String getFieldValue(Book book, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = book.getName();
        } else if (fieldName.equals("authorName")){
            theValue = book.getAuthorName().toString();
        } else {
            theValue = book.getGenre().toString();
        }
        return theValue;
    }

    /**
     * Search all Book fields for the given term.
     *
     * @param value The search term to look for.
     * @param allBooks The list of books to search.
     * @return  List of all books with at least one field containing the value.
     */
    public static ArrayList<Book> findByValue(String value, Iterable<Book> allBooks) {

        ArrayList<Book> results = new ArrayList<>();

        for (Book book : allBooks) {

            if (book.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(book);
            } else if (null !=book.getAuthorName() && book.getAuthorName().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(book);
            } else if (null!=book.getGenre() && book.getGenre().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(book);
            }

        }

        return results;
    }

}

