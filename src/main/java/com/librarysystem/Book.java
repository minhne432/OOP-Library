package com.librarysystem;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false; // Default: not borrowed
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    // Methods to manage borrowing status
    public void borrowBook() {
        if (!this.isBorrowed) {
            this.isBorrowed = true;
        } else {
            System.out.println("The book '" + title + "' is already borrowed.");
        }
    }

    public void returnBook() {
        if (this.isBorrowed) {
            this.isBorrowed = false;
        } else {
            System.out.println("The book '" + title + "' is not currently borrowed.");
        }
    }

    @Override
    public String toString() {
        return "'" + title + "' by " + author + " (ISBN: " + isbn + ") - Status: " + (isBorrowed ? "Borrowed" : "Available");
    }
}
