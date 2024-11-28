package mastric.library.view;

import mastric.library.dao.*;
import mastric.library.model.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
	static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        ReaderDAO readerDAO = new ReaderDAO();
        BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Statistic statistic = new Statistic();

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Quản lý sách");
            System.out.println("2. Quản lý độc giả");
            System.out.println("3. Quản lý mượn trả sách");
            System.out.println("4. Quản lý nhân viên");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    manageBooks(scanner, bookDAO);
                    break;
                case 2:
                    manageReaders(scanner, readerDAO);
                    break;
                case 3:
                    manageBorrowReturn(scanner, borrowRecordDAO);
                    break;
                case 4:
                    manageEmployees(scanner, employeeDAO);
                    break;
                case 5:
                    advancedSearch(scanner, statistic);
                    break;
                case 6:
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    //1. Quản lý sách
    private static void manageBooks(Scanner scanner, BookDAO bookDAO) {
        System.out.println("\n--- Quản lý sách ---");
        System.out.println("1. Thêm sách");
        System.out.println("2. Sửa thông tin sách");
        System.out.println("3. Xóa sách");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Nhập tên sách: ");
                String title = scanner.nextLine();
                System.out.print("Nhập tác giả: ");
                String author = scanner.nextLine();
                System.out.print("Nhập thể loại: ");
                String genre = scanner.nextLine();
                System.out.print("Nhập nhà xuất bản: ");
                String publisher = scanner.nextLine();
                System.out.print("Nhập ngày xuất bản: ");
                String date = scanner.nextLine();
                Date publishDate = null;
                try {
                    publishDate = formatter.parse(date);
                } catch (Exception e) {
                    System.out.println("Ngày không hợp lệ. Để trống ngày xuất bản.");
                }
                System.out.print("Nhập số lượng: ");
                int quantity = scanner.nextInt();
                
                
                bookDAO.addBook(title, author, genre, publisher, publishDate, quantity);
                System.out.println("Thêm sách thành công!");
                break;
            case 2:
                System.out.print("Nhập mã sách: ");
                int bookID = scanner.nextInt();
                scanner.nextLine(); 
                
                System.out.print("Nhập tên sách mới: ");
                String newTitle = scanner.nextLine();

                System.out.print("Nhập tác giả mới: ");
                String newAuthor = scanner.nextLine();

                System.out.print("Nhập thể loại mới: ");
                String newGenre = scanner.nextLine();

                System.out.print("Nhập nhà xuất bản mới: ");
                String newPublisher = scanner.nextLine();

                System.out.print("Nhập ngày xuất bản mới (dd/MM/yyyy): ");
                String newDate = scanner.nextLine();
                Date newPublishDate = null;
                try {
                    newPublishDate = formatter.parse(newDate);
                } catch (Exception e) {
                    System.out.println("Ngày không hợp lệ. Để trống ngày xuất bản.");
                }

                System.out.print("Nhập số lượng mới: ");
                int newQuantity = scanner.nextInt();

                bookDAO.updateBook(bookID, newTitle, newAuthor, newGenre, newPublisher, newPublishDate, newQuantity);
                System.out.println("Sửa thông tin sách thành công!");
                break;
            case 3:
                System.out.print("Nhập mã sách: ");
                bookID = scanner.nextInt();

                bookDAO.deleteBook(bookID);
                System.out.println("Xóa sách thành công!");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    //2. Quản lý độc giả
    private static void manageReaders(Scanner scanner, ReaderDAO readerDAO) {
        System.out.println("\n--- Quản lý độc giả ---");
        System.out.println("1. Đăng ký độc giả mới");
        System.out.println("2. Sửa thông tin độc giả");
        System.out.println("3. Xóa độc giả");
        System.out.println("4. Hiển thị danh sách độc giả");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Nhập họ tên: ");
                String fullName = scanner.nextLine();
                System.out.print("Nhập số điện thoại: ");
                String phone = scanner.nextLine();
                System.out.print("Nhập email: ");
                String email = scanner.nextLine();
                System.out.print("Nhập địa chỉ: ");
                String address = scanner.nextLine();
                		
                readerDAO.addReader(fullName, phone, email, address);
                System.out.println("Đăng ký độc giả thành công!");
                break;
            case 2:
                System.out.print("Nhập mã độc giả: ");
                int readerID = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nhập họ tên mới: ");
                String newFullName = scanner.nextLine();
                System.out.print("Nhập số điện thoại mới: ");
                String newPhone = scanner.nextLine();
                System.out.print("Nhập email mới: ");
                String newEmail = scanner.nextLine();
                System.out.print("Nhập địa chỉ mới: ");
                String newAddress = scanner.nextLine();

                readerDAO.updateReader(readerID, newFullName, newPhone, newEmail, newAddress);
                System.out.println("Sửa thông tin độc giả thành công!");
                break;
            case 3:
                System.out.print("Nhập mã độc giả: ");
                readerID = scanner.nextInt();

                readerDAO.deleteReader(readerID);
                System.out.println("Xóa độc giả thành công!");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    //3. Quản lý mượn trả sách
    private static void manageBorrowReturn(Scanner scanner, BorrowRecordDAO borrowRecordDAO) {
        System.out.println("\n--- Quản lý mượn trả sách ---");
        System.out.println("1. Hiển thị danh sách mượn trả sách");
        System.out.println("2. Ghi nhận mượn sách");
        System.out.println("3. Ghi nhận trả sách");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
        case 1:
            // Hiển thị danh sách từ BorrowRecordsView
            List<BorrowRecordsView> records = borrowRecordDAO.getBorrowRecordsView();
            if (records != null) {
                System.out.printf("%-10s %-20s %-20s %-15s %-15s %-15s %-10s\n",
                        "RecordID", "Reader Name", "Book Title", "Borrow Date", "Due Date", "Return Date", "Status");
                for (BorrowRecordsView record : records) {
                    System.out.printf("%-10d %-20s %-20s %-15s %-15s %-15s %-10s\n",
                            record.getRecordID(),
                            record.getReaderName(),
                            record.getBookTitle(),
                            record.getBorrowDate(),
                            record.getDueDate(),
                            record.getReturnDate(),
                            record.getStatus());
                }
            } else {
                System.out.println("Không có dữ liệu!");
            }
            break;
            case 2:
                System.out.print("Nhập tên độc giả: ");
                String readerName = scanner.nextLine();
                System.out.print("Nhập tên sách: ");
                String bookTitle = scanner.nextLine();
                System.out.print("Nhập ngày mượn (yyyy-MM-dd): ");
                String borrowDateStr = scanner.nextLine();
                System.out.print("Nhập ngày đến hạn trả (yyyy-MM-dd): ");
                String dueDateStr = scanner.nextLine();

                Date borrowDate = java.sql.Date.valueOf(borrowDateStr);
                Date dueDate = java.sql.Date.valueOf(dueDateStr);

                borrowRecordDAO.borrowBook(readerName, bookTitle, borrowDate, dueDate);
                break;

            case 3:
                System.out.print("Nhập tên độc giả: ");
                String returnReaderName = scanner.nextLine();
                System.out.print("Nhập tên sách: ");
                String returnBookTitle = scanner.nextLine();
                System.out.print("Nhập ngày trả (yyyy-MM-dd): ");
                String returnDateStr = scanner.nextLine();
                System.out.print("Nhập trạng thái (Returned, Lost): ");
                String status = scanner.nextLine();

                Date returnDate = java.sql.Date.valueOf(returnDateStr);
                borrowRecordDAO.returnBook(returnReaderName, returnBookTitle, returnDate, status);
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }


    /**4. Thống kê và báo cáo
    private static void generateReports(Scanner scanner, BorrowRecordDAO borrowRecordDAO, BookDAO bookDAO) {
        System.out.println("\n--- Thống kê và báo cáo ---");
        System.out.println("1. Thống kê sách được mượn nhiều nhất");
        System.out.println("2. Thống kê sách còn trong kho");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Nhập số lượng sách cần thống kê: ");
                int topN = scanner.nextInt();
                borrowRecordDAO.getTopBorrowedBooks(topN).forEach(result -> 
                    System.out.println(result[0] + " - " + result[1] + " lần mượn"));
                break;
            case 2:
                bookDAO.getBooksInStock().forEach(System.out::println);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }**/

    //4. Quản lý nhân viên
    private static void manageEmployees(Scanner scanner, EmployeeDAO employeeDAO) {
        System.out.println("\n--- Quản lý nhân viên ---");
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Xóa nhân viên");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Nhập họ tên: ");
                String fullName = scanner.nextLine();
                System.out.print("Nhập tên đăng nhập: ");
                String username = scanner.nextLine();
                System.out.print("Nhập mật khẩu: ");
                String password = scanner.nextLine();
                System.out.print("Nhập vai trò (Admin/Staff): ");
                String role = scanner.nextLine();

                employeeDAO.addEmployee(fullName, username, password, role);
                System.out.println("Thêm nhân viên thành công!");
                break;
            case 2:
                System.out.print("Nhập mã nhân viên: ");
                int employeeID = scanner.nextInt();

                employeeDAO.deleteEmployee(employeeID);
                System.out.println("Xóa nhân viên thành công!");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    //5. Tìm kiếm
    private static void advancedSearch(Scanner scanner, Statistic statistic) {
        System.out.println("\n--- Tìm kiếm ---");
        System.out.println("1. Tìm kiếm sách");
        System.out.println("2. Tìm kiếm độc giả");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Nhập tên sách: ");
                String title = scanner.nextLine();
                System.out.print("Nhập tác giả: ");
                String author = scanner.nextLine();
                System.out.print("Nhập thể loại: ");
                String genre = scanner.nextLine();

                statistic.advancedSearchBooks(title, author, genre).forEach(System.out::println);
                break;
            case 2:
                System.out.println("\n--- Tìm kiếm độc giả ---");
                System.out.print("Nhập tên độc giả (có thể bỏ qua): ");
                String name = scanner.nextLine().trim();
                name = name.isEmpty() ? null : name;

                System.out.print("Nhập số điện thoại (có thể bỏ qua): ");
                String phone = scanner.nextLine().trim();
                phone = phone.isEmpty() ? null : phone;

                System.out.print("Nhập email (có thể bỏ qua): ");
                String email = scanner.nextLine().trim();
                email = email.isEmpty() ? null : email;

                List<Reader> readers = statistic.searchReadersByDetails(name, phone, email);
                if (readers != null && !readers.isEmpty()) {
                    readers.forEach(reader -> System.out.println(
                        "ID: " + reader.getReaderID() +
                        ", Tên: " + reader.getFullName() +
                        ", SĐT: " + reader.getPhone() +
                        ", Email: " + reader.getEmail()
                    ));
                } else {
                    System.out.println("Không tìm thấy độc giả phù hợp.");
                }
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }
}
