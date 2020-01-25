package com.scalesampark.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.scalesampark.domains.Employee;

public class EmployeeDAO implements Dao<Employee> {
	Session session = null;
    
    private long addEmployee(Session session, Employee bean){
        Employee employee = new Employee();
        employee.setName(bean.getName());
        employee.setEmail(bean.getEmail());
        session.save(employee);
        return employee.getEmployeeId();
    }
    
    @Override
	public Employee get(long id) throws Exception {
		session = SessionUtil.getSession();    
        Query query = session.createQuery("from Employee where id = :id");
        query.setLong("id", id);
        List<Employee> employees =  query.list();
        if (employees != null && employees.size() > 0) {
        	session.close();
        	return employees.get(0);
        } else {
        	throw new Exception();
        }
	}

	@Override
	public List<Employee> getAll() {
		Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Employee");
        List<Employee> employees =  query.list();
        session.close();
        return employees;
	}

	@Override
	public long save(Employee t) {
		session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        long id = addEmployee(session, t);        
        tx.commit();
        session.close();
        return id;
	}

	@Override
	public void update(Employee emp) {
		/*Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
		String hql = "update Employee set name = :name, email=:email where id = :id";
		Query query = session.createQuery(hql);
		query.setString("name",emp.getName());
		query.setString("age",emp.getEmail());
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();*/
	}

	@Override
	public int delete(long t) {
		/*Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Employee where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
//        return rowCount;
*/		return 0;
	}
}
