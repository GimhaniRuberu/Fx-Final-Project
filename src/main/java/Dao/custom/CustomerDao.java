package Dao.custom;

import Dao.CrudDao;
import Entity.Customer;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer> {
    Customer get(String name) throws SQLException, ClassNotFoundException;
}
