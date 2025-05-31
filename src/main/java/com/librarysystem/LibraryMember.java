package com.librarysystem;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String memberId;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public LibraryMember(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getter methods
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks); // Return a copy to protect original list
    }

    // Methods to manage borrowing
    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            book.borrowBook(); // Mark the book as borrowed
            this.borrowedBooks.add(book);
            System.out.println(name + " borrowed the book: " + book.getTitle());
        } else {
            System.out.println("Cannot borrow the book '" + book.getTitle() + "'. It is already borrowed by someone else.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook(); // Mark the book as returned
            this.borrowedBooks.remove(book);
            System.out.println(name + " returned the book: " + book.getTitle());
        } else {
            System.out.println(name + " did not borrow the book '" + book.getTitle() + "'.");
        }
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " (ID: " + memberId + ") has not borrowed any books.");
        } else {
            System.out.println("Books borrowed by " + name + " (ID: " + memberId + "):");
            for (Book book : borrowedBooks) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    @Override
    public String toString() {
        return "Member: " + name + " (ID: " + memberId + ")";
    }
}
