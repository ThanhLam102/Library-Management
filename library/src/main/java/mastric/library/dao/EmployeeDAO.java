package mastric.library.dao;

import mastric.library.model.Employee;
import mastric.library.util.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDAO {

	public void addEmployee(String fullName, String username, String password, String role) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        Employee employee = new Employee(fullName, username, password, role);
	        session.save(employee);
	        transaction.commit();
	        System.out.println("Employee added successfully!");
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	public void deleteEmployee(int employeeID) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        Employee employee = session.get(Employee.class, employeeID);

	        if (employee != null) {
	            session.delete(employee);
	            transaction.commit();
	            System.out.println("Employee deleted successfully!");
	        } else {
	            System.out.println("Employee not found!");
	        }
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}
}
