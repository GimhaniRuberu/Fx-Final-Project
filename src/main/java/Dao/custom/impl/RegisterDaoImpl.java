package Dao.custom.impl;

import Dao.custom.RegisterDao;
import Dao.util.HibernateUtil;
import Entity.Register;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class RegisterDaoImpl implements RegisterDao {
    @Override
    public boolean save(Register entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Register entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public List<Register> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
