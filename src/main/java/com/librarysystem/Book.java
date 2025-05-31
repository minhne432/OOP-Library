// Book.java (Modified)
package com.librarysystem;

// implements Loanable
public class Book implements Loanable {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowedStatus; // Renamed to avoid conflict with isBorrowed() method in interface

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowedStatus = false;
    }

    // Retaining original getters
    @Override
    public String getTitle() { // Implementation from Loanable
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    // Implementing methods from Loanable
    @Override
    public void borrowItem() {
        if (!this.isBorrowedStatus) {
            this.isBorrowedStatus = true;
        } else {
            System.out.println("Item '" + title + "' is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (this.isBorrowedStatus) {
            this.isBorrowedStatus = false;
        } else {
            System.out.println("Item '" + title + "' is not currently borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowedStatus;
    }

    @Override
    public String toString() {
        return "'" + title + "' by " + author + " (ISBN: " + isbn + ") - Status: "
                + (isBorrowedStatus ? "Borrowed" : "Available");
    }
}
