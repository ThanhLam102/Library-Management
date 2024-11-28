package mastric.library.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import mastric.library.util.HibernateUtil;
import mastric.library.model.Book;

import java.util.List;
import java.util.Date;

public class BookDAO {
    public void addBook(String title, String author, String genre, String publisher, Date publishDate, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Book book = new Book(title, author, genre, publisher, publishDate, quantity);
            session.save(book);
            transaction.commit();
            System.out.println("Book added successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void updateBook(int bookID, String title, String author, String genre, String publisher, Date publishDate, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookID);

            if (book != null) {
                book.setTitle(title);
                book.setAuthor(author);
                book.setGenre(genre);
                book.setPublisher(publisher);
                book.setPublishDate(publishDate);
                book.setQuantity(quantity);

                session.update(book);
                transaction.commit();
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book not found!");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void deleteBook(int bookID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookID);

            if (book != null) {
                session.delete(book);
                transaction.commit();
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book not found!");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Book> searchBooksByTitle(String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "FROM Book WHERE title LIKE :title";
            List<Book> books = session.createQuery(hql, Book.class)
                                      .setParameter("title", "%" + title + "%")
                                      .list();
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public List<Book> getBooksSortedByPublishDate() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "FROM Book ORDER BY publishDate DESC";
            List<Book> books = session.createQuery(hql, Book.class).list();
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}

