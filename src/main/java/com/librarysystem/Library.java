// Library.java (will be extended later)
package com.librarysystem;

public class Library {
    public static void main(String[] args) {
        System.out.println("--- TEST PART 1 ---");

        // Create books
        Book book1 = new Book("Basic Java Programming", "John Doe", "978-1234567890");
        Book book2 = new Book("Data Structures and Algorithms", "Jane Smith", "978-0987654321");
        Book book3 = new Book("OOP in Java", "Alice Wonderland", "978-1122334455");

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
        member1.borrowBook(book1);
        member1.borrowBook(book2);
        member2.borrowBook(book1); // Try borrowing a book that's already borrowed
        member2.borrowBook(book3);

        System.out.println("\nBook status after borrowing:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("\nBooks borrowed by each member:");
        member1.displayBorrowedBooks();
        member2.displayBorrowedBooks();

        // Simulate returning books
        System.out.println("\n--- Book Returning Process ---");
        member1.returnBook(book1);
        member2.returnBook(book2); // Member 2 didn't borrow this book
        member1.returnBook(book3); // Member 1 didn't borrow this book

        System.out.println("\nBook status after returning:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("\nBooks borrowed by each member after returning:");
        member1.displayBorrowedBooks();
        member2.displayBorrowedBooks();

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

        member3.borrowBook(fictionBook);
        member3.borrowBook(nonFictionBook);

        System.out.println("\nReference Book Status After Borrowing:");
        System.out.println(fictionBook);
        System.out.println(nonFictionBook);

        member3.displayBorrowedBooks();

        // Verify that toString() of reference books works correctly
        System.out.println("\nRechecking Detailed Reference Book Information:");
        for (Book borrowed : member3.getBorrowedBooks()) {
            System.out.println(borrowed.toString()); // Will call toString() of FictionBook/NonFictionBook
        }

    }
}
