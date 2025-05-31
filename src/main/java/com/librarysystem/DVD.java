// DVD.java
package com.librarysystem;

public class DVD implements Loanable {
  private String title;
  private String director;
  private int runtimeMinutes;
  private boolean isBorrowedStatus;

  public DVD(String title, String director, int runtimeMinutes) {
    this.title = title;
    this.director = director;
    this.runtimeMinutes = runtimeMinutes;
    this.isBorrowedStatus = false;
  }

  // Getters
  @Override
  public String getTitle() { // Implementation from Loanable
    return title;
  }

  public String getDirector() {
    return director;
  }

  public int getRuntimeMinutes() {
    return runtimeMinutes;
  }

  // Implementing methods from Loanable
  @Override
  public void borrowItem() {
    if (!this.isBorrowedStatus) {
      this.isBorrowedStatus = true;
    } else {
      System.out.println("DVD '" + title + "' is already borrowed.");
    }
  }

  @Override
  public void returnItem() {
    if (this.isBorrowedStatus) {
      this.isBorrowedStatus = false;
    } else {
      System.out.println("DVD '" + title + "' is not currently borrowed.");
    }
  }

  @Override
  public boolean isBorrowed() {
    return isBorrowedStatus;
  }

  @Override
  public String toString() {
    return "DVD: '" + title + "' directed by " + director + " (Runtime: " + runtimeMinutes + " minutes) - Status: "
        + (isBorrowedStatus ? "Borrowed" : "Available");
  }
}
