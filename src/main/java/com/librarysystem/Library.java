// Library.java (Refactored for Part 4)
package com.librarysystem;

public class Library {
    public static void main(String[] args) {
        System.out.println("--- COMPLETE LIBRARY MANAGEMENT SYSTEM TEST (PART 4) ---");

        // 1. Create LibraryCatalog
        LibraryCatalog catalog = new LibraryCatalog();

        // 2. Add books and DVDs to the catalog
        Book book1 = new Book("Basic Java Programming", "John Doe", "978-1234567890");
        Book book2 = new Book("Data Structures and Algorithms", "Jane Smith", "978-0987654321");
        FictionBook fictionBook1 = new FictionBook("The Great Adventure", "Leo Tolstoy", "978-5555555555", "Adventure");
        NonFictionBook nonFictionBook1 = new NonFictionBook("World History", "Yuval Noah Harari", "978-6666666666",
                "930");
        DVD dvd1 = new DVD("The Matrix", "Wachowskis", 136);
        DVD dvd2 = new DVD("Inception", "Christopher Nolan", 148);

        catalog.addItem(book1);
        catalog.addItem(book2);
        catalog.addItem(fictionBook1);
        catalog.addItem(nonFictionBook1);
        catalog.addItem(dvd1);
        catalog.addItem(dvd2);
        catalog.addItem(dvd2); // Attempt to add an already existing item

        // 3. Add members to the catalog
        LibraryMember member1 = new LibraryMember("M001", "Nguyen Van An");
        LibraryMember member2 = new LibraryMember("M002", "Tran Thi Binh");
        LibraryMember member3 = new LibraryMember("M003", "Pham Van Cuong");

        catalog.addMember(member1);
        catalog.addMember(member2);
        catalog.addMember(member3);
        catalog.addMember(member3); // Attempt to add an already existing member

        // 4. Simulate full library workflow
        System.out.println("\n--- Starting library process ---");
        catalog.displayAllMembers();
        catalog.displayAllItems(); // Display all initial items
        catalog.displayAvailableItems(); // Display available items

        // Member searches for and borrows items
        System.out.println("\n--- An borrows 'Basic Java Programming' ---");
        Loanable itemToBorrowAn = catalog.findItem("Basic Java Programming");
        if (itemToBorrowAn != null) {
            catalog.lendItem(member1, itemToBorrowAn);
        } else {
            System.out.println("Could not find 'Basic Java Programming'.");
        }

        System.out.println("\n--- An borrows DVD 'The Matrix' ---");
        Loanable itemToBorrowAn2 = catalog.findItem("The Matrix");
        if (itemToBorrowAn2 != null) {
            catalog.lendItem(member1, itemToBorrowAn2);
        } else {
            System.out.println("Could not find DVD 'The Matrix'.");
        }

        System.out.println("\n--- Binh borrows 'World History' ---");
        Loanable itemToBorrowBinh = catalog.findItem("World History");
        if (itemToBorrowBinh != null) {
            catalog.lendItem(member2, itemToBorrowBinh);
        }

        System.out.println("\n--- Cuong attempts to borrow 'Basic Java Programming' (already borrowed by An) ---");
        Loanable itemToBorrowCuong = catalog.findItem("Basic Java Programming");
        if (itemToBorrowCuong != null) {
            catalog.lendItem(member3, itemToBorrowCuong);
        }

        // Display status again
        catalog.displayAvailableItems();
        System.out.println("\n--- Items borrowed by An ---");
        member1.displayBorrowedItems();
        System.out.println("\n--- Items borrowed by Binh ---");
        member2.displayBorrowedItems();
        System.out.println("\n--- Items borrowed by Cuong ---");
        member3.displayBorrowedItems();

        // Member returns items
        System.out.println("\n--- An returns 'Basic Java Programming' ---");
        catalog.acceptReturnedItem(member1, itemToBorrowAn); // itemToBorrowAn still holds the book reference

        System.out.println("\n--- Binh attempts to return a DVD not borrowed ---");
        catalog.acceptReturnedItem(member2, dvd2); // Binh didn't borrow dvd2

        // Display updated status
        catalog.displayAvailableItems();
        System.out.println("\n--- Items borrowed by An (after returning one) ---");
        member1.displayBorrowedItems();

        System.out.println("\n--- Cuong borrows 'Basic Java Programming' (after An returned it) ---");
        if (itemToBorrowCuong != null) { // itemToBorrowCuong still references "Basic Java Programming"
            catalog.lendItem(member3, itemToBorrowCuong);
        }
        System.out.println("\n--- Items borrowed by Cuong ---");
        member3.displayBorrowedItems();
        catalog.displayAvailableItems();

        System.out.println("\n--- Removing items from catalog ---");
        catalog.removeItem(dvd2); // Remove DVD Inception (available)
        catalog.removeItem(itemToBorrowAn2); // Attempt to remove DVD The Matrix (borrowed by An)
        catalog.displayAllItems();

        System.out.println("\n--- Removing members ---");
        catalog.removeMember(member2); // Binh has no borrowed items (assumed returned)
        catalog.removeMember(member1); // An still has The Matrix borrowed
        catalog.displayAllMembers();
    }
}
