// Loanable.java
package com.librarysystem;

public interface Loanable {
  void borrowItem();

  void returnItem();

  boolean isBorrowed();

  String getTitle(); // Thêm phương thức này để dễ dàng lấy tiêu đề

  String toString(); // Mọi đối tượng đều có, nhưng yêu cầu để đảm bảo có đại diện chuỗi
}