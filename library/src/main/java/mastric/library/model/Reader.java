package mastric.library.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Readers")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int readerID;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    @Temporal(TemporalType.DATE)
    private Date registerDate;

    // Constructors
    public Reader() {}

    public Reader(String fullName, String phone, String email, String address, Date registerDate) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.registerDate = registerDate;
    }

    // Getters and setters
    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}

