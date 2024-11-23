/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B14_MiniProject.models;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author huynq
 */
public class Book {

    // fields
    private int id;
    private int price;
    private String name;
    private String author;
    private String language;
    private String ISBN;
    private Date Publish_Date;   

    private double c;
    private double english;
    private double java;

    // constructors
    public Book() {

    }

    public Book(int id, int price, String name, String author, String language) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.author = author;
        this.language = language;
    }

//    public Student(int id, String name, String klass, String address) {
//        this.id = id;
//        this.name = name;
//        this.klass = klass;
//        this.address = address;
//    }

    public Book input(Scanner sc) {

        System.out.println("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine(); // ignore new line

        System.out.println("Enter price: ");
        int price = sc.nextInt();
        sc.nextLine(); // ignore new line
        
        System.out.println("Enter full name: ");
        String name = sc.nextLine();

        System.out.println("Enter author: ");
        String author = sc.nextLine();

        System.out.println("Enter language: ");
        String language = sc.nextLine();

        return new Book(id, price, name, author, language);
    }

    public void edit(Scanner sc) {

        System.out.println("Enter new price: ");
        this.price = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Enter new name: ");
        this.name = sc.nextLine();

        System.out.println("Enter new author: ");
        this.author = sc.nextLine();

        System.out.println("Enter new language: ");
        this.language = sc.nextLine();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getPublish_Date() {
        return Publish_Date;
    }

    public void setPublish_Date(Date Publish_Date) {
        this.Publish_Date = Publish_Date;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public double getJava() {
        return java;
    }

    // getters, setters
    public void setJava(double java) {
        this.java = java;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", price=" + price + "VND, name=" + name + ", author=" + author + ", language=" + language + '}';
    }






}