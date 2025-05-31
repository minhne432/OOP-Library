// Library.java (will be extended later)
package com.librarysystem;

public class Library {
    public static void main(String[] args) {
        System.out.println("--- TEST PART 1 ---");

        // Create books
        Loanable book1 = new Book("Basic Java Programming", "John Doe", "978-1234567890");
        Loanable book2 = new Book("Data Structures and Algorithms", "Jane Smith", "978-0987654321");
        Loanable book3 = new Book("OOP in Java", "Alice Wonderland", "978-1122334455");

        System.out.println("\nInitial book information:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        // Create library members
        LibraryMember member1 = new LibraryMember("M001", "Andrew Nguyen");
        LibraryMember member2 = new LibraryMember("M002", "Binh Tran");

        System.out.println("\nMember information:");
        System.out.println(member1);
        System.out.println(member2);

        // Simulate borrowing books
        System.out.println("\n--- Book Borrowing Process ---");
        member1.borrowItem(book1);
        member1.borrowItem(book2);
        member2.borrowItem(book1); // Try borrowing a book that's already borrowed
        member2.borrowItem(book3);

        System.out.println("\nBook status after borrowing:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("\nBooks borrowed by each member:");
        member1.displayBorrowedItems();
        member2.displayBorrowedItems();

        // Simulate returning books
        System.out.println("\n--- Book Returning Process ---");
        member1.returnItem(book1);
        member2.returnItem(book2); // Member 2 didn't borrow this book
        member1.returnItem(book3); // Member 1 didn't borrow this book

        System.out.println("\nBook status after returning:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("\nBooks borrowed by each member after returning:");
        member1.displayBorrowedItems();
        member2.displayBorrowedItems();

        System.out.println("\n\n--- PART 2 TESTING ---");

        // Create reference books
        FictionBook fictionBook = new FictionBook("The Great Adventure", "Leo Tolstoy", "978-5555555555", "Adventure");
        NonFictionBook nonFictionBook = new NonFictionBook("World History", "Yuval Noah Harari", "978-6666666666",
                "930");

        System.out.println("\nReference Book Information:");
        System.out.println(fictionBook);
        System.out.println(nonFictionBook);

        // Member borrows reference books
        System.out.println("\n--- Member Borrows Reference Books ---");
        LibraryMember member3 = new LibraryMember("M003", "John Smith");
        System.out.println(member3);

        member3.borrowItem(fictionBook);
        member3.borrowItem(nonFictionBook);

        System.out.println("\nReference Book Status After Borrowing:");
        System.out.println(fictionBook);
        System.out.println(nonFictionBook);

        member3.displayBorrowedItems();

        // Verify that toString() of reference books works correctly
        System.out.println("\nRechecking Detailed Reference Book Information:");
        for (Loanable borrowed : member3.getBorrowedItems()) {
            System.out.println(borrowed.toString()); // Will call toString() of FictionBook/NonFictionBook
        }


        System.out.println("\n\n--- TESTING PART 3 ---");

        // Create DVDs
        DVD dvd1 = new DVD("The Matrix", "Wachowskis", 136);
        DVD dvd2 = new DVD("Inception", "Christopher Nolan", 148);

        System.out.println("\nDVD Information:");
        System.out.println(dvd1);
        System.out.println(dvd2);

        // Create a new member or reuse an existing one
        LibraryMember member4 = new LibraryMember("M004", "Le Thi Diem");
        System.out.println(member4);

        System.out.println("\n--- Member borrows books and DVDs (Polymorphism) ---");
        // Retrieve a book from a previous part (e.g., book2)
        // Make sure book2 is not currently borrowed or has been returned
        if (book2.isBorrowed()) {
            // If book2 is currently borrowed by member1
            // Find member1 and request them to return book2, or you won't be able to borrow
            // it
            // Let's assume book2 has been returned or is available
            // Or create a new book:
            Book newBookForMember4 = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
            member4.borrowItem(newBookForMember4);
        } else {
            member4.borrowItem(book2); // Borrow an ordinary book
        }

        member4.borrowItem(fictionBook); // Borrow a fiction book
        member4.borrowItem(dvd1); // Borrow a DVD

        System.out.println("\nItems borrowed by " + member4.getName() + ":");
        member4.displayBorrowedItems(); // Will call toString() for Book, FictionBook, DVD

        System.out.println("\n--- Member returns DVD ---");
        member4.returnItem(dvd1);
        member4.displayBorrowedItems();

        // Check DVD status after returning
        System.out.println(dvd1);

    }
}
