package Bo.custom.impl;

import Bo.custom.CustomerBo;
import Bo.custom.ItemBo;
import Dao.DaoFactory;
import Dao.custom.CustomerDao;
import Dao.custom.ItemDao;
import Dao.util.DaoType;
import Entity.Customer;
import Entity.Item;
import dto.CustomerDto;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(
                dto.getCustomerId(),
                dto.getName(),
                dto.getContactNo(),
                dto.getEmail()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(
                dto.getCustomerId(),
                dto.getName(),
                dto.getContactNo(),
                dto.getEmail()
        ));
    }

    @Override
    public boolean deleteCustomer(String code) throws SQLException, ClassNotFoundException {
        return customerDao.delete(code);
    }

    @Override
    public List<CustomerDto> allCustomer() throws SQLException, ClassNotFoundException {
        List<Customer> entityList = customerDao.getAll();
        List<CustomerDto> list = new ArrayList<>();
        for (Customer customer:entityList) {
            list.add(new CustomerDto(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getContactNo(),
                    customer.getEmail()
            ));
        }
        return list;

    }
}
