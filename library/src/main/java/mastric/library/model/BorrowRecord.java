package mastric.library.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BorrowRecords")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordID;

    @ManyToOne
    @JoinColumn(name = "ReaderID", nullable = false)
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "BookID", nullable = false)
    private Book book;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date borrowDate;

    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dueDate;

    private String status; // Borrowed, Returned, Overdue, Lost

    // Constructors
    public BorrowRecord() {}

    public BorrowRecord(Reader reader, Book book, Date borrowDate, Date dueDate, String status) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and setters
    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

