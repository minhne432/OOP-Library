# OOP-Library

---

### Phần 1: Cơ bản - Lớp `Book` và `LibraryMember` (Tính đóng gói)

Tính đóng gói được thể hiện qua việc sử dụng các thuộc tính `private` và cung cấp các phương thức `public` (getters/setters) để truy cập và thay đổi trạng thái của đối tượng một cách có kiểm soát.

#### 1. Lớp `Book.java`

```java
// Book.java
package com.librarysystem;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false; // Mặc định sách chưa được mượn
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    // Methods to manage borrowing status
    public void borrowBook() {
        if (!this.isBorrowed) {
            this.isBorrowed = true;
        } else {
            System.out.println("Sách '" + title + "' đã được mượn rồi.");
        }
    }

    public void returnBook() {
        if (this.isBorrowed) {
            this.isBorrowed = false;
        } else {
            System.out.println("Sách '" + title + "' không ở trạng thái đang được mượn.");
        }
    }

    @Override
    public String toString() {
        return "'" + title + "' của " + author + " (ISBN: " + isbn + ") - Trạng thái: " + (isBorrowed ? "Đã mượn" : "Có sẵn");
    }
}
```

#### 2. Lớp `LibraryMember.java`

```java
// LibraryMember.java
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
        return new ArrayList<>(borrowedBooks); // Trả về một bản sao để bảo vệ danh sách gốc
    }

    // Methods to manage borrowing
    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            book.borrowBook(); // Đánh dấu sách đã được mượn
            this.borrowedBooks.add(book);
            System.out.println(name + " đã mượn sách: " + book.getTitle());
        } else {
            System.out.println("Không thể mượn sách '" + book.getTitle() + "'. Sách đã được người khác mượn.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook(); // Đánh dấu sách đã được trả
            this.borrowedBooks.remove(book);
            System.out.println(name + " đã trả sách: " + book.getTitle());
        } else {
            System.out.println(name + " không mượn sách '" + book.getTitle() + "' này.");
        }
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " (ID: " + memberId + ") chưa mượn cuốn sách nào.");
        } else {
            System.out.println("Sách đã mượn bởi " + name + " (ID: " + memberId + "):");
            for (Book book : borrowedBooks) {
                System.out.println("- " + book.getTitle() + " của " + book.getAuthor());
            }
        }
    }

    @Override
    public String toString() {
        return "Thành viên: " + name + " (ID: " + memberId + ")";
    }
}
```

#### 3. Lớp `Library.java` (để kiểm thử Phần 1)

```java
// Library.java (sẽ được mở rộng sau)
package com.librarysystem;

public class Library {
    public static void main(String[] args) {
        System.out.println("--- KIỂM THỬ PHẦN 1 ---");

        // Tạo sách
        Book book1 = new Book("Lập trình Java cơ bản", "John Doe", "978-1234567890");
        Book book2 = new Book("Cấu trúc dữ liệu và giải thuật", "Jane Smith", "978-0987654321");
        Book book3 = new Book("OOP trong Java", "Alice Wonderland", "978-1122334455");

        System.out.println("\nThông tin sách ban đầu:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        // Tạo thành viên thư viện
        LibraryMember member1 = new LibraryMember("M001", "Nguyễn Văn An");
        LibraryMember member2 = new LibraryMember("M002", "Trần Thị Bình");

        System.out.println("\nThông tin thành viên:");
        System.out.println(member1);
        System.out.println(member2);

        // Mô phỏng mượn sách
        System.out.println("\n--- Quá trình mượn sách ---");
        member1.borrowBook(book1);
        member1.borrowBook(book2);
        member2.borrowBook(book1); // Thử mượn sách đã được mượn
        member2.borrowBook(book3);

        System.out.println("\nTrạng thái sách sau khi mượn:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("\nSách đã mượn của từng thành viên:");
        member1.displayBorrowedBooks();
        member2.displayBorrowedBooks();

        // Mô phỏng trả sách
        System.out.println("\n--- Quá trình trả sách ---");
        member1.returnBook(book1);
        member2.returnBook(book2); // Thành viên 2 không mượn sách này
        member1.returnBook(book3); // Thành viên 1 không mượn sách này

        System.out.println("\nTrạng thái sách sau khi trả:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("\nSách đã mượn của từng thành viên sau khi trả:");
        member1.displayBorrowedBooks();
        member2.displayBorrowedBooks();
    }
}
```

---

### Phần 2: Mở rộng Thư viện - `FictionBook` và `NonFictionBook` (Tính kế thừa)

Tính kế thừa cho phép các lớp con (`FictionBook`, `NonFictionBook`) thừa hưởng các thuộc tính và phương thức từ lớp cha (`Book`), đồng thời có thể mở rộng bằng cách thêm các thuộc tính và phương thức riêng hoặc ghi đè phương thức của lớp cha.

#### 1. Lớp `FictionBook.java`

```java
// FictionBook.java
package com.librarysystem;

public class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String isbn, String genre) {
        super(title, author, isbn); // Gọi constructor của lớp cha (Book)
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        // Gọi toString() của lớp cha và thêm thông tin về thể loại
        return super.toString().replaceFirst "\\) -", ") - Thể loại: " + genre + " -");
    }
}
```

#### 2. Lớp `NonFictionBook.java`

```java
// NonFictionBook.java
package com.librarysystem;

public class NonFictionBook extends Book {
    private String deweyDecimalCode;

    public NonFictionBook(String title, String author, String isbn, String deweyDecimalCode) {
        super(title, author, isbn); // Gọi constructor của lớp cha (Book)
        this.deweyDecimalCode = deweyDecimalCode;
    }

    public String getDeweyDecimalCode() {
        return deweyDecimalCode;
    }

    @Override
    public String toString() {
        // Gọi toString() của lớp cha và thêm thông tin về mã Dewey
        return super.toString().replaceFirst("\\) -", ") - Dewey Code: " + deweyDecimalCode + " -");
    }
}
```

#### 3. Cập nhật `Library.java` (để kiểm thử Phần 2)

Thêm vào cuối phương thức `main` của lớp `Library.java`:

```java
// ... (Mã từ Phần 1) ...

        System.out.println("\n\n--- KIỂM THỬ PHẦN 2 ---");

        // Tạo sách chuyên khảo
        FictionBook fictionBook = new FictionBook("Cuộc phiêu lưu kỳ thú", "Leo Tolstoy", "978-5555555555", "Phiêu lưu");
        NonFictionBook nonFictionBook = new NonFictionBook("Lịch sử Thế giới", "Yuval Noah Harari", "978-6666666666", "930");

        System.out.println("\nThông tin sách chuyên khảo:");
        System.out.println(fictionBook);
        System.out.println(nonFictionBook);

        // Thành viên mượn sách chuyên khảo
        System.out.println("\n--- Thành viên mượn sách chuyên khảo ---");
        LibraryMember member3 = new LibraryMember("M003", "Phạm Văn Cường");
        System.out.println(member3);

        member3.borrowBook(fictionBook);
        member3.borrowBook(nonFictionBook);

        System.out.println("\nTrạng thái sách chuyên khảo sau khi mượn:");
        System.out.println(fictionBook);
        System.out.println(nonFictionBook);

        member3.displayBorrowedBooks();

        // Kiểm tra toString() của sách chuyên khảo đã hoạt động đúng
        System.out.println("\nKiểm tra lại thông tin chi tiết sách chuyên khảo:");
        for (Book borrowed : member3.getBorrowedBooks()) {
             System.out.println(borrowed.toString()); // Sẽ gọi toString() của FictionBook/NonFictionBook
        }
```

---

### Phần 3: Tương tác Nâng cao - Interface `Loanable` và Lớp `DVD` (Tính trừu tượng & Đa hình)

- **Tính trừu tượng**: Interface `Loanable` định nghĩa một "hợp đồng" cho các đối tượng có thể cho mượn mà không quan tâm đến chi tiết triển khai cụ thể.
- **Tính đa hình**: `LibraryMember` có thể quản lý một danh sách các `Loanable` items, có thể là `Book`, `FictionBook`, `NonFictionBook`, hoặc `DVD`, và gọi các phương thức như `borrowItem()`, `returnItem()` trên chúng mà không cần biết kiểu cụ thể của đối tượng tại thời điểm biên dịch.

#### 1. Interface `Loanable.java`

```java
// Loanable.java
package com.librarysystem;

public interface Loanable {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
    String getTitle(); // Thêm phương thức này để dễ dàng lấy tiêu đề
    String toString(); // Mọi đối tượng đều có, nhưng yêu cầu để đảm bảo có đại diện chuỗi
}
```

#### 2. Sửa đổi lớp `Book.java`

```java
// Book.java (Sửa đổi)
package com.librarysystem;

// implements Loanable
public class Book implements Loanable {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowedStatus; // Đổi tên để tránh xung đột với phương thức isBorrowed() của interface

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowedStatus = false;
    }

    // Giữ lại các getters cũ
    @Override
    public String getTitle() { // Triển khai từ Loanable
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    // Triển khai các phương thức từ Loanable
    @Override
    public void borrowItem() {
        if (!this.isBorrowedStatus) {
            this.isBorrowedStatus = true;
        } else {
            System.out.println("Vật phẩm '" + title + "' đã được mượn rồi.");
        }
    }

    @Override
    public void returnItem() {
        if (this.isBorrowedStatus) {
            this.isBorrowedStatus = false;
        } else {
            System.out.println("Vật phẩm '" + title + "' không ở trạng thái đang được mượn.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowedStatus;
    }

    @Override
    public String toString() {
        return "'" + title + "' của " + author + " (ISBN: " + isbn + ") - Trạng thái: " + (isBorrowedStatus ? "Đã mượn" : "Có sẵn");
    }
}
```

**Lưu ý quan trọng:**

- Thuộc tính `isBorrowed` trong `Book` được đổi tên thành `isBorrowedStatus` để tránh trùng tên với phương thức `isBorrowed()` mà interface `Loanable` yêu cầu.
- Phương thức `borrowBook()` và `returnBook()` trong `Book` đã được đổi tên thành `borrowItem()` và `returnItem()` để phù hợp với interface `Loanable`.
- Lớp `FictionBook` và `NonFictionBook` không cần thay đổi nhiều vì chúng kế thừa các phương thức đã được cập nhật từ `Book`.

#### 3. Lớp `DVD.java`

```java
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
    public String getTitle() { // Triển khai từ Loanable
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    // Triển khai các phương thức từ Loanable
    @Override
    public void borrowItem() {
        if (!this.isBorrowedStatus) {
            this.isBorrowedStatus = true;
        } else {
            System.out.println("DVD '" + title + "' đã được mượn rồi.");
        }
    }

    @Override
    public void returnItem() {
        if (this.isBorrowedStatus) {
            this.isBorrowedStatus = false;
        } else {
            System.out.println("DVD '" + title + "' không ở trạng thái đang được mượn.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowedStatus;
    }

    @Override
    public String toString() {
        return "DVD: '" + title + "' đạo diễn bởi " + director + " (Thời lượng: " + runtimeMinutes + " phút) - Trạng thái: " + (isBorrowedStatus ? "Đã mượn" : "Có sẵn");
    }
}
```

#### 4. Sửa đổi lớp `LibraryMember.java`

```java
// LibraryMember.java (Sửa đổi)
package com.librarysystem;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String memberId;
    private String name;
    // private ArrayList<Book> borrowedBooks; // Thay đổi dòng này
    private ArrayList<Loanable> borrowedItems; // Thành danh sách các Loanable

    public LibraryMember(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        // this.borrowedBooks = new ArrayList<>(); // Thay đổi dòng này
        this.borrowedItems = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    // public List<Book> getBorrowedBooks() { // Thay đổi phương thức này
    public List<Loanable> getBorrowedItems() {
        return new ArrayList<>(borrowedItems); // Trả về bản sao
    }

    // public void borrowBook(Book book) { // Thay đổi phương thức này
    public void borrowItem(Loanable item) {
        if (!item.isBorrowed()) {
            item.borrowItem(); // Gọi phương thức của Loanable
            this.borrowedItems.add(item);
            System.out.println(name + " đã mượn: " + item.getTitle());
        } else {
            System.out.println("Không thể mượn '" + item.getTitle() + "'. Vật phẩm đã được người khác mượn.");
        }
    }

    // public void returnBook(Book book) { // Thay đổi phương thức này
    public void returnItem(Loanable item) {
        if (borrowedItems.contains(item)) {
            item.returnItem(); // Gọi phương thức của Loanable
            this.borrowedItems.remove(item);
            System.out.println(name + " đã trả: " + item.getTitle());
        } else {
            System.out.println(name + " không mượn vật phẩm '" + item.getTitle() + "' này.");
        }
    }

    // public void displayBorrowedBooks() { // Thay đổi phương thức này
    public void displayBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println(name + " (ID: " + memberId + ") chưa mượn vật phẩm nào.");
        } else {
            System.out.println("Vật phẩm đã mượn bởi " + name + " (ID: " + memberId + "):");
            for (Loanable item : borrowedItems) {
                // toString() của đối tượng cụ thể (Book, DVD) sẽ được gọi (đa hình)
                System.out.println("- " + item.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "Thành viên: " + name + " (ID: " + memberId + ")";
    }
}
```

#### 5. Cập nhật `Library.java` (để kiểm thử Phần 3)

Thêm vào cuối phương thức `main` của lớp `Library.java`:

```java
// ... (Mã từ Phần 1 và 2) ...
        // Cần cập nhật các lời gọi phương thức trong Phần 1 và 2
        // ví dụ: member1.borrowBook(book1) -> member1.borrowItem(book1)
        //         member1.displayBorrowedBooks() -> member1.displayBorrowedItems()
        // Hãy đảm bảo bạn đã cập nhật các phần code trước đó cho phù hợp.

        System.out.println("\n\n--- KIỂM THỬ PHẦN 3 ---");

        // Tạo DVD
        DVD dvd1 = new DVD("The Matrix", "Wachowskis", 136);
        DVD dvd2 = new DVD("Inception", "Christopher Nolan", 148);

        System.out.println("\nThông tin DVD:");
        System.out.println(dvd1);
        System.out.println(dvd2);

        // Tạo thành viên mới hoặc sử dụng thành viên cũ
        LibraryMember member4 = new LibraryMember("M004", "Lê Thị Diễm");
        System.out.println(member4);

        System.out.println("\n--- Thành viên mượn sách và DVD (Đa hình) ---");
        // Lấy lại một cuốn sách từ phần trước (ví dụ book2)
        // Đảm bảo book2 chưa được mượn hoặc đã được trả
        if (book2.isBorrowed()) { // Nếu book2 đang bị member1 mượn
            // Tìm member1 và yêu cầu trả book2, nếu không bạn sẽ không mượn được
            // Giả sử book2 đã được trả hoặc chưa ai mượn lại
            // Nếu member1 vẫn giữ book2, bạn cần member1.returnItem(book2);
            // Để đơn giản, ta giả định book2 đang có sẵn
            // Hoặc tạo một cuốn sách mới:
            Book newBookForMember4 = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
            member4.borrowItem(newBookForMember4);
        } else {
            member4.borrowItem(book2); // Mượn một cuốn sách thường
        }

        member4.borrowItem(fictionBook); // Mượn một cuốn sách chuyên khảo (FictionBook)
        member4.borrowItem(dvd1);        // Mượn một DVD

        System.out.println("\nCác vật phẩm đã mượn của " + member4.getName() + ":");
        member4.displayBorrowedItems(); // Sẽ gọi toString() tương ứng cho Book, FictionBook, DVD

        System.out.println("\n--- Thành viên trả DVD ---");
        member4.returnItem(dvd1);
        member4.displayBorrowedItems();

        // Kiểm tra trạng thái DVD sau khi trả
        System.out.println(dvd1);
```

**QUAN TRỌNG:** Sau khi thay đổi `Book` và `LibraryMember` để làm việc với `Loanable`, bạn cần **cập nhật lại code kiểm thử của Phần 1 và Phần 2** trong `Library.java`.
Ví dụ:

- `member1.borrowBook(book1)` thành `member1.borrowItem(book1)`
- `member1.returnBook(book1)` thành `member1.returnItem(book1)`
- `member1.displayBorrowedBooks()` thành `member1.displayBorrowedItems()`
- `ArrayList<Book> borrowedBooks` trong `LibraryMember` thành `ArrayList<Loanable> borrowedItems`.

---

### Phần 4: Thư viện - `LibraryCatalog` (Tổng hợp)

Lớp `LibraryCatalog` sẽ quản lý danh mục các `Loanable` items và danh sách các `LibraryMember`.

#### 1. Lớp `LibraryCatalog.java`

```java
// LibraryCatalog.java
package com.librarysystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator; // Để xóa an toàn khỏi danh sách khi lặp

public class LibraryCatalog {
    private ArrayList<Loanable> items;
    private ArrayList<LibraryMember> members;

    public LibraryCatalog() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // Quản lý vật phẩm
    public void addItem(Loanable item) {
        if (!items.contains(item)) {
            items.add(item);
            System.out.println("Đã thêm vào catalog: " + item.getTitle());
        } else {
            System.out.println("Vật phẩm '" + item.getTitle() + "' đã có trong catalog.");
        }
    }

    public void removeItem(Loanable item) {
        if (items.contains(item)) {
            if (!item.isBorrowed()) {
                items.remove(item);
                System.out.println("Đã xóa khỏi catalog: " + item.getTitle());
            } else {
                System.out.println("Không thể xóa '" + item.getTitle() + "'. Vật phẩm đang được mượn.");
            }
        } else {
            System.out.println("Vật phẩm '" + item.getTitle() + "' không tìm thấy trong catalog.");
        }
    }

    // Quản lý thành viên
    public void addMember(LibraryMember member) {
        if (!members.contains(member)) {
            members.add(member);
            System.out.println("Đã thêm thành viên: " + member.getName());
        } else {
            System.out.println("Thành viên '" + member.getName() + "' (ID: " + member.getMemberId() + ") đã tồn tại.");
        }
    }

    public void removeMember(LibraryMember member) {
        if (members.contains(member)) {
            if (member.getBorrowedItems().isEmpty()) {
                members.remove(member);
                System.out.println("Đã xóa thành viên: " + member.getName());
            } else {
                System.out.println("Không thể xóa thành viên '" + member.getName() + "'. Thành viên đang mượn vật phẩm.");
            }
        } else {
            System.out.println("Thành viên '" + member.getName() + "' không tìm thấy.");
        }
    }

    // Tìm kiếm và hiển thị
    public Loanable findItem(String title) {
        for (Loanable item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null; // Không tìm thấy
    }

    public void displayAvailableItems() {
        System.out.println("\n--- Các vật phẩm có sẵn trong thư viện ---");
        boolean found = false;
        for (Loanable item : items) {
            if (!item.isBorrowed()) {
                System.out.println(item.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Hiện tại không có vật phẩm nào có sẵn.");
        }
    }

    public void displayAllItems() {
        System.out.println("\n--- Tất cả vật phẩm trong thư viện ---");
        if (items.isEmpty()) {
            System.out.println("Catalog rỗng.");
            return;
        }
        for (Loanable item : items) {
            System.out.println(item.toString());
        }
    }

    public void displayAllMembers() {
        System.out.println("\n--- Tất cả thành viên trong thư viện ---");
        if (members.isEmpty()) {
            System.out.println("Chưa có thành viên nào.");
            return;
        }
        for (LibraryMember member : members) {
            System.out.println(member.toString());
        }
    }


    // Nghiệp vụ mượn/trả
    public void lendItem(LibraryMember member, Loanable item) {
        if (item == null) {
            System.out.println("Lỗi: Vật phẩm không hợp lệ.");
            return;
        }
        if (!items.contains(item)) {
            System.out.println("Vật phẩm '" + item.getTitle() + "' không có trong catalog của thư viện.");
            return;
        }
        if (!members.contains(member)) {
            System.out.println("Thành viên '" + member.getName() + "' không phải là thành viên của thư viện này.");
            return;
        }

        if (!item.isBorrowed()) {
            member.borrowItem(item); // Phương thức này đã bao gồm item.borrowItem() bên trong
        } else {
            System.out.println("Vật phẩm '" + item.getTitle() + "' hiện đang được mượn.");
        }
    }

    public void acceptReturnedItem(LibraryMember member, Loanable item) {
         if (item == null) {
            System.out.println("Lỗi: Vật phẩm không hợp lệ.");
            return;
        }
        if (!members.contains(member)) {
            System.out.println("Thành viên '" + member.getName() + "' không phải là thành viên của thư viện này.");
            return;
        }
        // Không cần kiểm tra item có trong catalog không, vì nếu member mượn thì nó phải từ catalog
        member.returnItem(item); // Phương thức này đã bao gồm item.returnItem() bên trong
    }
}
```

#### 2. Cập nhật `Library.java` (để kiểm thử Phần 4 - tái cấu trúc `main`)

Phương thức `main` bây giờ sẽ chủ yếu tương tác với `LibraryCatalog`.

```java
// Library.java (Tái cấu trúc cho Phần 4)
package com.librarysystem;

public class Library {
    public static void main(String[] args) {
        System.out.println("--- KIỂM THỬ HỆ THỐNG QUẢN LÝ THƯ VIỆN HOÀN CHỈNH (PHẦN 4) ---");

        // 1. Tạo LibraryCatalog
        LibraryCatalog catalog = new LibraryCatalog();

        // 2. Thêm các loại sách và DVD vào catalog
        Book book1 = new Book("Lập trình Java cơ bản", "John Doe", "978-1234567890");
        Book book2 = new Book("Cấu trúc dữ liệu và giải thuật", "Jane Smith", "978-0987654321");
        FictionBook fictionBook1 = new FictionBook("Cuộc phiêu lưu kỳ thú", "Leo Tolstoy", "978-5555555555", "Phiêu lưu");
        NonFictionBook nonFictionBook1 = new NonFictionBook("Lịch sử Thế giới", "Yuval Noah Harari", "978-6666666666", "930");
        DVD dvd1 = new DVD("The Matrix", "Wachowskis", 136);
        DVD dvd2 = new DVD("Inception", "Christopher Nolan", 148);

        catalog.addItem(book1);
        catalog.addItem(book2);
        catalog.addItem(fictionBook1);
        catalog.addItem(nonFictionBook1);
        catalog.addItem(dvd1);
        catalog.addItem(dvd2);
        catalog.addItem(dvd2); // Thử thêm lại vật phẩm đã có

        // 3. Thêm thành viên vào catalog
        LibraryMember member1 = new LibraryMember("M001", "Nguyễn Văn An");
        LibraryMember member2 = new LibraryMember("M002", "Trần Thị Bình");
        LibraryMember member3 = new LibraryMember("M003", "Phạm Văn Cường");

        catalog.addMember(member1);
        catalog.addMember(member2);
        catalog.addMember(member3);
        catalog.addMember(member3); // Thử thêm lại thành viên đã có

        // 4. Mô phỏng toàn bộ quy trình
        System.out.println("\n--- Bắt đầu quy trình thư viện ---");
        catalog.displayAllMembers();
        catalog.displayAllItems(); // Hiển thị tất cả item ban đầu
        catalog.displayAvailableItems(); // Hiển thị các item có sẵn ban đầu

        // Thành viên tìm và mượn vật phẩm
        System.out.println("\n--- An mượn sách 'Lập trình Java cơ bản' ---");
        Loanable itemToBorrowAn = catalog.findItem("Lập trình Java cơ bản");
        if (itemToBorrowAn != null) {
            catalog.lendItem(member1, itemToBorrowAn);
        } else {
            System.out.println("Không tìm thấy sách 'Lập trình Java cơ bản'.");
        }

        System.out.println("\n--- An mượn DVD 'The Matrix' ---");
        Loanable itemToBorrowAn2 = catalog.findItem("The Matrix");
        if (itemToBorrowAn2 != null) {
            catalog.lendItem(member1, itemToBorrowAn2);
        } else {
            System.out.println("Không tìm thấy DVD 'The Matrix'.");
        }

        System.out.println("\n--- Bình mượn sách 'Lịch sử Thế giới' ---");
        Loanable itemToBorrowBinh = catalog.findItem("Lịch sử Thế giới");
        if (itemToBorrowBinh != null) {
            catalog.lendItem(member2, itemToBorrowBinh);
        }

        System.out.println("\n--- Cường thử mượn sách 'Lập trình Java cơ bản' (đã được An mượn) ---");
        Loanable itemToBorrowCuong = catalog.findItem("Lập trình Java cơ bản");
        if (itemToBorrowCuong != null) {
            catalog.lendItem(member3, itemToBorrowCuong);
        }

        // Hiển thị lại trạng thái
        catalog.displayAvailableItems();
        System.out.println("\n--- Vật phẩm An đã mượn ---");
        member1.displayBorrowedItems();
        System.out.println("\n--- Vật phẩm Bình đã mượn ---");
        member2.displayBorrowedItems();
        System.out.println("\n--- Vật phẩm Cường đã mượn ---");
        member3.displayBorrowedItems();

        // Thành viên trả vật phẩm
        System.out.println("\n--- An trả sách 'Lập trình Java cơ bản' ---");
        catalog.acceptReturnedItem(member1, itemToBorrowAn); // itemToBorrowAn vẫn giữ tham chiếu đến sách

        System.out.println("\n--- Bình trả sách không có mượn ---");
        catalog.acceptReturnedItem(member2, dvd2); // Bình không mượn dvd2

        // Hiển thị lại trạng thái
        catalog.displayAvailableItems();
        System.out.println("\n--- Vật phẩm An đã mượn (sau khi trả 1) ---");
        member1.displayBorrowedItems();

        System.out.println("\n--- Cường mượn sách 'Lập trình Java cơ bản' (sau khi An trả) ---");
        if (itemToBorrowCuong != null) { // itemToBorrowCuong vẫn là "Lập trình Java cơ bản"
            catalog.lendItem(member3, itemToBorrowCuong);
        }
        System.out.println("\n--- Vật phẩm Cường đã mượn ---");
        member3.displayBorrowedItems();
        catalog.displayAvailableItems();

        System.out.println("\n--- Xóa vật phẩm khỏi catalog ---");
        catalog.removeItem(dvd2); // Xóa DVD Inception (đang có sẵn)
        catalog.removeItem(itemToBorrowAn2); // Thử xóa DVD The Matrix (An đang mượn)
        catalog.displayAllItems();

        System.out.println("\n--- Xóa thành viên ---");
        catalog.removeMember(member2); // Bình không mượn gì (giả sử đã trả hết hoặc không mượn)
        catalog.removeMember(member1); // An đang mượn The Matrix
        catalog.displayAllMembers();

    }
}
```

---

### Deliverables - Báo cáo (Giải thích)

#### 1. Tính đóng gói (Encapsulation) được sử dụng trong lớp `Book` và `LibraryMember` như thế nào?

Tính đóng gói được áp dụng trong `Book` và `LibraryMember` bằng cách:

- **Khai báo thuộc tính là `private`**: Các thuộc tính như `title`, `author`, `isbn`, `isBorrowedStatus` trong `Book`, và `memberId`, `name`, `borrowedItems` trong `LibraryMember` được đặt là `private`. Điều này ngăn chặn việc truy cập và sửa đổi trực tiếp từ bên ngoài lớp, bảo vệ dữ liệu khỏi những thay đổi không mong muốn hoặc không hợp lệ.
- **Cung cấp phương thức `public` (Getters/Setters)**:
  - **Getters**: Các phương thức như `getTitle()`, `getAuthor()`, `isBorrowed()` trong `Book`, hay `getMemberId()`, `getName()`, `getBorrowedItems()` trong `LibraryMember` cho phép bên ngoài đọc giá trị của các thuộc tính một cách có kiểm soát.
  - **Phương thức nghiệp vụ**: Các phương thức như `borrowItem()`, `returnItem()` trong `Book` (và `Loanable` nói chung), và `borrowItem(Loanable item)`, `returnItem(Loanable item)` trong `LibraryMember` không chỉ thay đổi trạng thái (ví dụ `isBorrowedStatus`) mà còn có thể chứa logic kiểm tra (ví dụ: không cho mượn sách đã được mượn). Điều này đảm bảo rằng dữ liệu chỉ được thay đổi theo các quy tắc đã định.
- **Ẩn giấu chi tiết triển khai**: Người dùng lớp `Book` hay `LibraryMember` không cần biết chi tiết cách danh sách `borrowedItems` được lưu trữ (ví dụ: là `ArrayList`) hay cách trạng thái `isBorrowedStatus` được quản lý. Họ chỉ tương tác qua các giao diện công khai.

=> **Lợi ích**: Tăng tính bảo mật dữ liệu, dễ bảo trì (thay đổi cách triển khai bên trong không ảnh hưởng đến code bên ngoài nếu giao diện công khai không đổi), và code dễ hiểu hơn.

#### 2. Tính kế thừa (Inheritance) được sử dụng để tạo `FictionBook` và `NonFictionBook` như thế nào? Lợi ích là gì?

Tính kế thừa được sử dụng như sau:

- Lớp `FictionBook` và `NonFictionBook` được khai báo với từ khóa `extends Book`. Điều này có nghĩa là chúng **thừa hưởng tất cả các thuộc tính và phương thức `public` và `protected`** từ lớp cha `Book` (ví dụ: `title`, `author`, `isbn`, `isBorrowedStatus`, `borrowItem()`, `returnItem()`, `getTitle()`, `isBorrowed()`).
- Trong constructor của `FictionBook` và `NonFictionBook`, lời gọi `super(title, author, isbn);` được sử dụng để **gọi constructor của lớp cha `Book`**, đảm bảo các thuộc tính kế thừa được khởi tạo đúng cách.
- Các lớp con **thêm các thuộc tính riêng**: `FictionBook` có thêm `genre`, `NonFictionBook` có thêm `deweyDecimalCode`.
- Các lớp con **ghi đè (override) phương thức `toString()`** của lớp cha để cung cấp thông tin mô tả chi tiết hơn, bao gồm cả các thuộc tính riêng của chúng, đồng thời vẫn tận dụng `super.toString()` để lấy phần thông tin chung từ lớp `Book`.

**Lợi ích của tính kế thừa:**

- **Tái sử dụng mã (Code Reusability)**: Không cần viết lại các thuộc tính và phương thức chung đã có trong `Book`. Điều này giảm thiểu sự trùng lặp mã và tiết kiệm thời gian phát triển.
- **Tổ chức code rõ ràng và phân cấp (Hierarchical Classification)**: Tạo ra một cấu trúc phân cấp tự nhiên ("is-a" relationship: `FictionBook` _is a_ `Book`). Điều này làm cho hệ thống dễ hiểu và quản lý hơn.
- **Tính đa hình (Polymorphism)**: Một đối tượng `FictionBook` hoặc `NonFictionBook` có thể được coi như một đối tượng `Book` (hoặc `Loanable` sau này). Điều này cho phép viết code linh hoạt hơn, ví dụ, một danh sách các `Book` có thể chứa cả `FictionBook` và `NonFictionBook`.
- **Dễ mở rộng và bảo trì (Extensibility and Maintainability)**: Khi cần thêm một loại sách mới (ví dụ: `ReferenceBook`), chỉ cần tạo một lớp mới kế thừa từ `Book` và thêm các đặc điểm riêng mà không ảnh hưởng nhiều đến các lớp đã có.

#### 3. Tính đa hình (Polymorphism) được thể hiện khi một `LibraryMember` mượn các loại `Loanable` items khác nhau như thế nào?

Tính đa hình được thể hiện rõ ràng khi `LibraryMember` tương tác với các `Loanable` items:

1.  **Khai báo kiểu cha/interface**: Thuộc tính `borrowedItems` trong `LibraryMember` được khai báo là `ArrayList<Loanable>`. Điều này có nghĩa là danh sách này có thể chứa bất kỳ đối tượng nào thuộc lớp triển khai interface `Loanable` (ví dụ: `Book`, `FictionBook`, `NonFictionBook`, `DVD`).
2.  **Gọi phương thức chung**:
    - Trong phương thức `borrowItem(Loanable item)` và `returnItem(Loanable item)` của `LibraryMember`, khi gọi `item.borrowItem()` hoặc `item.returnItem()` hoặc `item.isBorrowed()` hoặc `item.getTitle()`, Java sẽ tự động xác định **phiên bản phương thức cụ thể của đối tượng thực tế** (runtime type) đang được tham chiếu bởi biến `item`.
    - Ví dụ, nếu `item` là một đối tượng `DVD`, thì `dvd.borrowItem()` sẽ được gọi. Nếu `item` là một `FictionBook`, thì `fictionBook.borrowItem()` (thừa kế từ `Book`) sẽ được gọi.
3.  **Phương thức `displayBorrowedItems()`**: Khi lặp qua danh sách `borrowedItems` và gọi `item.toString()`, Java cũng sẽ gọi phương thức `toString()` đã được ghi đè (overridden) tương ứng của lớp cụ thể (`Book`, `FictionBook`, `DVD`). Điều này cho phép hiển thị thông tin chi tiết và phù hợp cho từng loại vật phẩm mà không cần kiểm tra kiểu `instanceof` một cách tường minh trong vòng lặp.

=> **Ví dụ cụ thể**:
Khi `member.borrowItem(myFictionBook)`: `myFictionBook` (một `FictionBook`) được truyền vào phương thức `borrowItem`. Bên trong phương thức này, `myFictionBook.borrowItem()` được gọi. Do `FictionBook` kế thừa `borrowItem` từ `Book` (đã được điều chỉnh để phù hợp `Loanable`), phiên bản đó sẽ chạy.
Khi `member.borrowItem(myDVD)`: `myDVD` (một `DVD`) được truyền vào. `myDVD.borrowItem()` được gọi, và đây là phiên bản `borrowItem` được triển khai riêng trong lớp `DVD`.

=> **Lợi ích**: Code trở nên linh hoạt, dễ mở rộng. `LibraryMember` không cần biết chi tiết về các loại `Loanable` cụ thể mà nó quản lý. Nếu sau này có thêm loại `Magazine` (cũng `Loanable`), `LibraryMember` vẫn hoạt động mà không cần sửa đổi.

#### 4. Interface `Loanable` đại diện cho tính trừu tượng (Abstraction) như thế nào?

Interface `Loanable` đại diện cho tính trừu tượng bằng cách:

- **Định nghĩa một "hợp đồng" (Contract)**: `Loanable` chỉ rõ _những gì_ một đối tượng có thể cho mượn phải làm (các hành vi) thông qua các phương thức trừu tượng (không có phần thân) như `borrowItem()`, `returnItem()`, `isBorrowed()`, và `getTitle()`. Nó không quan tâm _làm thế nào_ các hành vi đó được thực hiện.
- **Ẩn giấu chi tiết triển khai (Implementation Hiding)**: Bất kỳ lớp nào muốn được coi là "có thể cho mượn" đều phải `implements Loanable` và cung cấp phần triển khai cụ thể cho các phương thức này. Ví dụ, cách `Book` quản lý trạng thái `isBorrowed` có thể khác với cách `DVD` làm (mặc dù trong ví dụ này chúng tương tự nhau, nhưng chúng có thể khác). Người dùng (ví dụ: lớp `LibraryMember` hay `LibraryCatalog`) chỉ cần biết đối tượng đó là `Loanable` và có thể gọi các phương thức này.
- **Tập trung vào các đặc điểm thiết yếu**: `Loanable` chỉ tập trung vào các hành động cốt lõi liên quan đến việc cho mượn, bỏ qua các chi tiết khác không liên quan của `Book` (như `author`, `isbn`) hay `DVD` (như `director`, `runtimeMinutes`) ở cấp độ interface.

=> **Lợi ích**:

- **Giảm sự phụ thuộc**: Các lớp như `LibraryMember` và `LibraryCatalog` phụ thuộc vào abstraction (`Loanable`) thay vì các lớp cụ thể (`Book`, `DVD`). Điều này làm cho hệ thống linh hoạt hơn.
- **Cho phép đa hình**: Như đã giải thích ở trên, việc sử dụng interface là nền tảng cho tính đa hình.
- **Thiết kế linh hoạt**: Dễ dàng thêm các loại vật phẩm mới có thể cho mượn (ví dụ: `Magazine`, `SoftwareLicense`) vào hệ thống bằng cách cho chúng triển khai interface `Loanable` mà không cần thay đổi nhiều ở các lớp hiện có như `LibraryMember` hay `LibraryCatalog`.

#### 5. Những thách thức đã gặp và cách bạn vượt qua chúng.

- **Thách thức 1: Đổi tên phương thức và cập nhật lời gọi.**
  - Khi giới thiệu interface `Loanable` với các phương thức `borrowItem()` và `returnItem()`, cần phải đổi tên các phương thức tương ứng trong lớp `Book` (từ `borrowBook` thành `borrowItem`). Điều này kéo theo việc phải cập nhật tất cả các lời gọi đến các phương thức đó trong `LibraryMember` và code kiểm thử ban đầu.
  - **Giải pháp**: Cẩn thận rà soát và sử dụng tính năng "Rename" (Refactor > Rename) của IDE nếu có để đảm bảo tất cả các tham chiếu đều được cập nhật. Kiểm tra kỹ lưỡng sau khi thay đổi.
- **Thách thức 2: Xung đột tên giữa thuộc tính và phương thức của interface.**
  - Lớp `Book` ban đầu có thuộc tính `isBorrowed` (boolean) và phương thức getter `isBorrowed()`. Interface `Loanable` cũng yêu cầu một phương thức `isBorrowed()`. Điều này gây mơ hồ.
  - **Giải pháp**: Đổi tên thuộc tính trong lớp `Book` thành `isBorrowedStatus` để phương thức `isBorrowed()` có thể được triển khai đúng theo yêu cầu của interface mà không bị che bởi tên thuộc tính.
- **Thách thức 3: Quản lý tham chiếu đối tượng và trạng thái.**
  - Khi một thành viên mượn một cuốn sách, cả đối tượng `Book` và danh sách sách mượn của `LibraryMember` đều cần được cập nhật. Đảm bảo rằng trạng thái `isBorrowed` của sách và danh sách sách của thành viên luôn đồng bộ.
  - **Giải pháp**: Trong phương thức `borrowItem` của `LibraryMember`, sau khi kiểm tra sách có sẵn không, gọi `item.borrowItem()` để cập nhật trạng thái của chính vật phẩm đó, rồi mới thêm vào danh sách `borrowedItems` của thành viên. Tương tự với `returnItem`.
- **Thách thức 4: Logic trong `LibraryCatalog` (ví dụ `findItem`, `lendItem`).**
  - `findItem` cần duyệt qua danh sách các `Loanable` và so sánh tiêu đề. Interface `Loanable` cần cung cấp phương thức `getTitle()`.
  - `lendItem` cần kiểm tra xem vật phẩm có trong catalog không, thành viên có hợp lệ không, và vật phẩm có sẵn không trước khi cho mượn.
  - **Giải pháp**: Thêm phương thức `getTitle()` vào interface `Loanable` và triển khai nó ở các lớp con. Xây dựng logic kiểm tra cẩn thận từng bước trong các phương thức của `LibraryCatalog`, đảm bảo các điều kiện tiên quyết được đáp ứng.
- **Thách thức 5: Tính nhất quán khi tái cấu trúc `main` cho `LibraryCatalog`.**
  - Việc chuyển từ kiểm thử từng phần riêng lẻ sang sử dụng `LibraryCatalog` để quản lý toàn bộ đòi hỏi phải sắp xếp lại luồng kiểm thử, đảm bảo các đối tượng được tạo và thêm vào catalog đúng thứ tự.
  - **Giải pháp**: Lập kế hoạch các bước kiểm thử cho `LibraryCatalog` một cách có hệ thống: tạo catalog, thêm item, thêm member, mượn, trả, hiển thị, xóa. Đảm bảo các đối tượng tham chiếu (như `itemToBorrowAn` trong `main`) được sử dụng đúng cách.

Việc chia nhỏ bài toán thành các phần và kiểm thử từng phần giúp dễ dàng phát hiện và sửa lỗi sớm, làm cho quá trình xây dựng các phần phức tạp hơn sau này trở nên trơn tru hơn.
