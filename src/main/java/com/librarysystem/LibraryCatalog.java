// LibraryCatalog.java
package com.librarysystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator; // To safely remove from the list during iteration

public class LibraryCatalog {
  private ArrayList<Loanable> items;
  private ArrayList<LibraryMember> members;

  public LibraryCatalog() {
    this.items = new ArrayList<>();
    this.members = new ArrayList<>();
  }

  // Item management
  public void addItem(Loanable item) {
    if (!items.contains(item)) {
      items.add(item);
      System.out.println("Added to catalog: " + item.getTitle());
    } else {
      System.out.println("Item '" + item.getTitle() + "' already exists in the catalog.");
    }
  }

  public void removeItem(Loanable item) {
    if (items.contains(item)) {
      if (!item.isBorrowed()) {
        items.remove(item);
        System.out.println("Removed from catalog: " + item.getTitle());
      } else {
        System.out.println("Cannot remove '" + item.getTitle() + "'. Item is currently borrowed.");
      }
    } else {
      System.out.println("Item '" + item.getTitle() + "' not found in the catalog.");
    }
  }

  // Member management
  public void addMember(LibraryMember member) {
    if (!members.contains(member)) {
      members.add(member);
      System.out.println("Member added: " + member.getName());
    } else {
      System.out.println("Member '" + member.getName() + "' (ID: " + member.getMemberId() + ") already exists.");
    }
  }

  public void removeMember(LibraryMember member) {
    if (members.contains(member)) {
      if (member.getBorrowedItems().isEmpty()) {
        members.remove(member);
        System.out.println("Member removed: " + member.getName());
      } else {
        System.out.println("Cannot remove member '" + member.getName() + "'. Member currently has borrowed items.");
      }
    } else {
      System.out.println("Member '" + member.getName() + "' not found.");
    }
  }

  // Search and display
  public Loanable findItem(String title) {
    for (Loanable item : items) {
      if (item.getTitle().equalsIgnoreCase(title)) {
        return item;
      }
    }
    return null; // Not found
  }

  public void displayAvailableItems() {
    System.out.println("\n--- Available items in the library ---");
    boolean found = false;
    for (Loanable item : items) {
      if (!item.isBorrowed()) {
        System.out.println(item.toString());
        found = true;
      }
    }
    if (!found) {
      System.out.println("No available items at the moment.");
    }
  }

  public void displayAllItems() {
    System.out.println("\n--- All items in the library ---");
    if (items.isEmpty()) {
      System.out.println("The catalog is empty.");
      return;
    }
    for (Loanable item : items) {
      System.out.println(item.toString());
    }
  }

  public void displayAllMembers() {
    System.out.println("\n--- All library members ---");
    if (members.isEmpty()) {
      System.out.println("There are no members yet.");
      return;
    }
    for (LibraryMember member : members) {
      System.out.println(member.toString());
    }
  }

  // Lending/Returning operations
  public void lendItem(LibraryMember member, Loanable item) {
    if (item == null) {
      System.out.println("Error: Invalid item.");
      return;
    }
    if (!items.contains(item)) {
      System.out.println("Item '" + item.getTitle() + "' is not in the library catalog.");
      return;
    }
    if (!members.contains(member)) {
      System.out.println("Member '" + member.getName() + "' is not a member of this library.");
      return;
    }

    if (!item.isBorrowed()) {
      member.borrowItem(item); // This method already includes item.borrowItem() inside
    } else {
      System.out.println("Item '" + item.getTitle() + "' is currently borrowed.");
    }
  }

  public void acceptReturnedItem(LibraryMember member, Loanable item) {
    if (item == null) {
      System.out.println("Error: Invalid item.");
      return;
    }
    if (!members.contains(member)) {
      System.out.println("Member '" + member.getName() + "' is not a member of this library.");
      return;
    }
    // No need to check if item is in the catalog, since if a member borrowed it, it
    // must be from the catalog
    member.returnItem(item); // This method already includes item.returnItem() inside
  }
}
