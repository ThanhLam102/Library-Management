CREATE TABLE Books (
    BookID INT PRIMARY KEY IDENTITY(1,1),
    Title NVARCHAR(255) NOT NULL,
    Author NVARCHAR(255) NOT NULL,
    Genre NVARCHAR(100),
    Publisher NVARCHAR(255),
    PublishDate DATE,
    Quantity INT
);

INSERT INTO Books (Title, Author, Genre, Publisher, PublishDate, Quantity)
VALUES
('Java Programming', 'John Doe', 'Programming', 'Tech Books', '2022-01-15', 10),
('Hibernate Mastery', 'Jane Smith', 'Programming', 'Code House', '2021-05-10', 5),
('SQL Essentials', 'Alice Brown', 'Database', 'Data Publishers', '2020-11-25', 7);

CREATE TABLE Readers (
    ReaderID INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(255) NOT NULL,
    Phone NVARCHAR(20) NOT NULL,
    Email NVARCHAR(255) NOT NULL,
    Address NVARCHAR(255),
    RegisterDate DATE DEFAULT GETDATE()
);

INSERT INTO Readers (FullName, Phone, Email, Address)
VALUES
('Nguyen Van A', '0901234567', 'vana@gmail.com', '123 Main Street'),
('Le Thi B', '0902345678', 'leb@gmail.com', '456 Elm Street'),
('Tran Van C', '0903456789', 'tranc@gmail.com', '789 Oak Street');

CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(255) NOT NULL,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    Role NVARCHAR(50) NOT NULL CHECK (Role IN ('Admin', 'Staff'))
);

INSERT INTO Employees (FullName, Username, Password, Role)
VALUES
('Admin User', 'admin', 'admin123', 'Admin'),
('Staff User', 'staff', 'staff123', 'Staff');

CREATE TABLE BorrowRecords (
    RecordID INT PRIMARY KEY IDENTITY(1,1),
    ReaderID INT NOT NULL FOREIGN KEY REFERENCES Readers(ReaderID),
    BookID INT NOT NULL FOREIGN KEY REFERENCES Books(BookID),
    BorrowDate DATE NOT NULL,
    ReturnDate DATE,
    DueDate DATE NOT NULL,
    Status NVARCHAR(50) NOT NULL CHECK (Status IN ('Borrowed', 'Returned', 'Overdue', 'Lost'))
);

INSERT INTO BorrowRecords (ReaderID, BookID, BorrowDate, DueDate, Status)
VALUES
(1, 1, '2023-11-01', '2023-11-15', 'Borrowed'),
(2, 2, '2023-10-15', '2023-10-30', 'Returned'),
(3, 3, '2023-09-10', '2023-09-24', 'Overdue');

CREATE VIEW BorrowRecordsView AS
SELECT 
    br.RecordID,
    r.FullName AS ReaderName,
    b.Title AS BookTitle,
    br.BorrowDate,
    br.DueDate,
    br.ReturnDate,
    br.Status
FROM BorrowRecords br
JOIN Readers r ON br.ReaderID = r.ReaderID
JOIN Books b ON br.BookID = b.BookID;
