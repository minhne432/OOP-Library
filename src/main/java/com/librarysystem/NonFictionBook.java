package com.librarysystem;

public class NonFictionBook extends Book {
    private String deweyDecimalCode;

    public NonFictionBook(String title, String author, String isbn, String deweyDecimalCode) {
        super(title, author, isbn); // Call the constructor of the superclass (Book)
        this.deweyDecimalCode = deweyDecimalCode;
    }

    public String getDeweyDecimalCode() {
        return deweyDecimalCode;
    }

    @Override
    public String toString() {
        // Call toString() of the superclass and add Dewey code information
        return super.toString().replaceFirst("\\) -", ") - Dewey Code: " + deweyDecimalCode + " -");
    }
}
