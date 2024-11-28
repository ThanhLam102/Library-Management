package mastric.library.dao;

import java.util.List;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mastric.library.model.*;
import mastric.library.model.BorrowRecord;
import mastric.library.util.HibernateUtil;

public class Statistic {

	/**Thống kê top N sách được mượn nhiều nhất
	public List<Object[]> getTopBorrowedBooks(int topN) {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        String hql = "SELECT b.title, COUNT(r.recordID) AS borrowCount " +
	                     "FROM BorrowRecord r JOIN r.book b " +
	                     "GROUP BY b.title " +
	                     "ORDER BY borrowCount DESC";
	        List<Object[]> results = session.createQuery(hql)
	                                        .setMaxResults(topN)
	                                        .list();
	        return results;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}
	
	//Thống kê sách còn trong kho
	public List<Book> getBooksInStock() {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        String hql = "FROM Book WHERE quantity > 0";
	        return session.createQuery(hql, Book.class).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}

	//Thống kê sách đã được mượn
	public List<Object[]> getBooksBorrowed() {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        String hql = "SELECT b.title, SUM(b.quantity) AS borrowedQuantity " +
	                     "FROM BorrowRecord r JOIN r.book b " +
	                     "GROUP BY b.title";
	        return session.createQuery(hql).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}
	
	//Độc giả chưa trả sách
	public List<Object[]> getReadersWithUnreturnedBooks() {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        String hql = "SELECT r.reader.fullName, COUNT(r.recordID) AS unreturnedCount " +
	                     "FROM BorrowRecord r " +
	                     "WHERE r.status = 'Borrowed' " +
	                     "GROUP BY r.reader.fullName";
	        return session.createQuery(hql).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}
	
	//Độc giả trả trễ:
	public List<Object[]> getReadersWithOverdueBooks() {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        String hql = "SELECT r.reader.fullName, r.dueDate, r.returnDate " +
	                     "FROM BorrowRecord r " +
	                     "WHERE r.status = 'Overdue'";
	        return session.createQuery(hql).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}**/
	
	//Tìm kiếm sách
	public List<Book> advancedSearchBooks(String title, String author, String genre) {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        String hql = "FROM Book WHERE " +
	                     "(title LIKE :title OR :title IS NULL) AND " +
	                     "(author LIKE :author OR :author IS NULL) AND " +
	                     "(genre LIKE :genre OR :genre IS NULL)";
	        return session.createQuery(hql, Book.class)
	                      .setParameter("title", title != null ? "%" + title + "%" : null)
	                      .setParameter("author", author != null ? "%" + author + "%" : null)
	                      .setParameter("genre", genre != null ? "%" + genre + "%" : null)
	                      .list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}

	// Tìm kiếm độc giả
	public List<Reader> searchReadersByDetails(String name, String phone, String email) {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        String hql = "FROM Reader WHERE " +
	                     "(fullName LIKE :name OR :name IS NULL) AND " +
	                     "(phone LIKE :phone OR :phone IS NULL) AND " +
	                     "(email LIKE :email OR :email IS NULL)";
	        return session.createQuery(hql, Reader.class)
	                      .setParameter("name", name != null ? "%" + name + "%" : null)
	                      .setParameter("phone", phone != null ? "%" + phone + "%" : null)
	                      .setParameter("email", email != null ? "%" + email + "%" : null)
	                      .list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}


	/**Ghi log lịch sử mượn trả sách
	public void logBorrowReturn(int recordID, String operation) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        BorrowRecord record = session.get(BorrowRecord.class, recordID);

	        if (record != null) {
	            System.out.println("LOG: Operation: " + operation + ", Book: " + record.getBook().getTitle() +
	                               ", Reader: " + record.getReader().getFullName() +
	                               ", Date: " + new Date());
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	//Ghi log hoạt động nhân viên
	public void logEmployeeActivity(int employeeID, String activity) {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        Employee employee = session.get(Employee.class, employeeID);
	        if (employee != null) {
	            System.out.println("LOG: Employee: " + employee.getFullName() +
	                               ", Activity: " + activity +
	                               ", Date: " + new Date());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}**/

}
