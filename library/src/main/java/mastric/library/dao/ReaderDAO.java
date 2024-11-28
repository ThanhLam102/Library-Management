package mastric.library.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import mastric.library.model.Reader;
import mastric.library.util.HibernateUtil;
public class ReaderDAO {
	
	public void addReader(String fullName, String phone, String email, String address) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        Reader reader = new Reader(fullName, phone, email, address, new Date());
	        session.save(reader);
	        transaction.commit();
	        System.out.println("Reader added successfully!");
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	public void updateReader(int readerID, String fullName, String phone, String email, String address) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        Reader reader = session.get(Reader.class, readerID);

	        if (reader != null) {
	            reader.setFullName(fullName);
	            reader.setPhone(phone);
	            reader.setEmail(email);
	            reader.setAddress(address);

	            session.update(reader);
	            transaction.commit();
	            System.out.println("Reader updated successfully!");
	        } else {
	            System.out.println("Reader not found!");
	        }
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	public void deleteReader(int readerID) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        Reader reader = session.get(Reader.class, readerID);

	        if (reader != null) {
	            session.delete(reader);
	            transaction.commit();
	            System.out.println("Reader deleted successfully!");
	        } else {
	            System.out.println("Reader not found!");
	        }
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	public List<Reader> searchReadersByName(String name) {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        String hql = "FROM Reader WHERE fullName LIKE :name";
	        List<Reader> readers = session.createQuery(hql, Reader.class)
	                                      .setParameter("name", "%" + name + "%")
	                                      .list();
	        return readers;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}
}
