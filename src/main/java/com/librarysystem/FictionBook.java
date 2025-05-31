package com.librarysystem;

public class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String isbn, String genre) {
        super(title, author, isbn); // Call the constructor of the superclass (Book)
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        // Call toString() of the superclass and add genre information
        return super.toString().replaceFirst("\\) -", ") - Genre: " + genre + " -");
    }
}
