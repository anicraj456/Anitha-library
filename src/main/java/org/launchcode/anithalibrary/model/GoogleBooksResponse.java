package org.launchcode.anithalibrary.model;

public class GoogleBooksResponse {
    private GoogleBookItem[] items;

    public GoogleBookItem[] getItems() {
        return items;
    }

    public void setItems(GoogleBookItem[] items) {
        this.items = items;
    }
}