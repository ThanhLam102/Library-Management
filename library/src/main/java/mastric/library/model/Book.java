package mastric.library.model;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookID;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String genre;
    private String publisher;

    @Temporal(TemporalType.DATE)
    private Date publishDate;

    private int quantity;

    // Constructors
    public Book() {}

    public Book(String title, String author, String genre, String publisher, Date publishDate, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
