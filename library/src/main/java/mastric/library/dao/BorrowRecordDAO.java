package mastric.library.dao;

import mastric.library.util.HibernateUtil;
import mastric.library.model.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BorrowRecordDAO {
	
	public List<BorrowRecordsView> getBorrowRecordsView() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "FROM BorrowRecordsView";
            Query<BorrowRecordsView> query = session.createQuery(hql, BorrowRecordsView.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

	public void borrowBook(String readerName, String bookTitle, Date borrowDate, Date dueDate) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();

	        // Tìm độc giả theo tên
	        String readerHQL = "FROM Reader WHERE fullName = :readerName";
	        Reader reader = session.createQuery(readerHQL, Reader.class)
	                               .setParameter("readerName", readerName)
	                               .uniqueResult();

	        // Tìm sách theo tên
	        String bookHQL = "FROM Book WHERE title = :bookTitle";
	        Book book = session.createQuery(bookHQL, Book.class)
	                           .setParameter("bookTitle", bookTitle)
	                           .uniqueResult();

	        if (reader != null && book != null && book.getQuantity() > 0) {
	            BorrowRecord record = new BorrowRecord(reader, book, borrowDate, dueDate, "Borrowed");
	            session.save(record);

	            book.setQuantity(book.getQuantity() - 1);
	            session.update(book);

	            transaction.commit();
	            System.out.println("Book borrowed successfully!");
	        } else {
	            System.out.println("Borrow failed: Invalid reader or book, or book is out of stock.");
	        }
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	public void returnBook(String readerName, String bookTitle, Date returnDate, String status) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();

	        // Tìm bản ghi mượn theo độc giả và sách
	        String hql = "FROM BorrowRecord WHERE reader.fullName = :readerName AND book.title = :bookTitle AND status = 'Borrowed'";
	        BorrowRecord record = session.createQuery(hql, BorrowRecord.class)
	                                     .setParameter("readerName", readerName)
	                                     .setParameter("bookTitle", bookTitle)
	                                     .uniqueResult();

	        if (record != null) {
	            record.setReturnDate(returnDate);
	            record.setStatus(status);

	            Book book = record.getBook();
	            book.setQuantity(book.getQuantity() + 1);
	            session.update(book);

	            session.update(record);
	            transaction.commit();
	            System.out.println("Book returned successfully!");
	        } else {
	            System.out.println("Return failed: Invalid record or book not in 'Borrowed' status.");
	        }
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}


	public long calculateLateFee(int recordID) {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        BorrowRecord record = session.get(BorrowRecord.class, recordID);

	        if (record != null && record.getReturnDate() != null) {
	            long overdueDays = TimeUnit.DAYS.convert(
	                record.getReturnDate().getTime() - record.getDueDate().getTime(),
	                TimeUnit.MILLISECONDS
	            );

	            if (overdueDays > 0) {
	                return overdueDays * 10000; // 10,000 VNĐ/ngày
	            }
	        }
	        return 0; // No late fee
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    } finally {
	        session.close();
	    }
	}
	
	
}
