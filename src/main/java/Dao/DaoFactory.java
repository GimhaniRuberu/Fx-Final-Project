package Dao;

import Dao.custom.impl.*;
import Dao.util.DaoType;


public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return daoFactory != null ? daoFactory : (daoFactory = new DaoFactory());
    }

    public <T extends SuperDao> T getDao(DaoType type) {
        switch (type) {
            case REGISTER:
                return (T) new RegisterDaoImpl();
            case CHANGEPW:
                return (T) new ChangeDaoImpl();
            case ITEM:
                return (T) new ItemDaoImpl();
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case PLACEORDER:
                return (T) new PlaceOrderDaoImpl();
            case ORDERS:
                return (T) new OrderDaoImpl();
        }
        return null;
    }
}



