package Dao.custom.impl;

import Dao.custom.ChangeDao;
import Dao.util.HibernateUtil;
import Entity.ChangePw;
import Entity.Register;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ChangeDaoImpl implements ChangeDao {
    @Override
    public boolean save(ChangePw entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Register fff = session.find(Register.class, entity.getId());
        entity.setRegister(fff);
        session.save(entity);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(ChangePw entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ChangePw> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM ChangePw ");
        List<ChangePw> list = query.list();
        session.close();
        return list;

    }
}
