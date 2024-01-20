package Dao.custom.impl;

import Dao.custom.CustomerDao;
import Dao.util.HibernateUtil;
import Entity.Customer;
import dto.ItemDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    public ItemDto searchItem(String id) {
        return null;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, entity.getCustomerId());
        customer.setCustomerId(entity.getCustomerId());
        customer.setName(entity.getName());
        customer.setContactNo(entity.getContactNo());
        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Customer.class,value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Customer");
        List<Customer> list = query.list();
        session.close();
        return list;
    }

}
