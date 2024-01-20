package Dao.custom.impl;

import Dao.custom.ItemDao;

import Dao.util.CrudUtil;

import Dao.util.HibernateUtil;
import Entity.Item;
import dto.ItemDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
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
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        Item item = session.find(Item.class, entity.getItemCode());
        item.setItemCode(entity.getItemCode());
        item.setCategory(entity.getCategory());
        item.setName(entity.getName());
        session.save(item);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Item.class,value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Item ");
        List<Item> list = query.list();
        session.close();
        return list;
    }


    //@Override
//    public List<Item> getAll() throws SQLException, ClassNotFoundException {
//        List<Item> list = new ArrayList<>();
//        String sql = "SELECT * FROM Item";
////
////        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = CrudUtil.execute(sql);
//        while (resultSet.next()){
//            list.add(new Item(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3)
//            ));
//        }
//
//        return list;
//    }
}
