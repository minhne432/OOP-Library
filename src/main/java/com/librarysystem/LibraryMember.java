// LibraryMember.java (Modified)
package com.librarysystem;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String memberId;
    private String name;
    // private ArrayList<Book> borrowedBooks; // Replaced line
    private ArrayList<Loanable> borrowedItems; // Changed to a list of Loanable items

    public LibraryMember(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        // this.borrowedBooks = new ArrayList<>(); // Replaced line
        this.borrowedItems = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    // public List<Book> getBorrowedBooks() { // Replaced method
    public List<Loanable> getBorrowedItems() {
        return new ArrayList<>(borrowedItems); // Return a copy
    }

    // public void borrowBook(Book book) { // Replaced method
    public void borrowItem(Loanable item) {
        if (!item.isBorrowed()) {
            item.borrowItem(); // Call method from Loanable
            this.borrowedItems.add(item);
            System.out.println(name + " has borrowed: " + item.getTitle());
        } else {
            System.out.println("Cannot borrow '" + item.getTitle() + "'. The item is already borrowed.");
        }
    }

    // public void returnBook(Book book) { // Replaced method
    public void returnItem(Loanable item) {
        if (borrowedItems.contains(item)) {
            item.returnItem(); // Call method from Loanable
            this.borrowedItems.remove(item);
            System.out.println(name + " has returned: " + item.getTitle());
        } else {
            System.out.println(name + " did not borrow the item '" + item.getTitle() + "'.");
        }
    }

    // public void displayBorrowedBooks() { // Replaced method
    public void displayBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println(name + " (ID: " + memberId + ") has not borrowed any items.");
        } else {
            System.out.println("Items borrowed by " + name + " (ID: " + memberId + "):");
            for (Loanable item : borrowedItems) {
                // Calls the appropriate toString() method (polymorphism)
                System.out.println("- " + item.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "Member: " + name + " (ID: " + memberId + ")";
    }
}
