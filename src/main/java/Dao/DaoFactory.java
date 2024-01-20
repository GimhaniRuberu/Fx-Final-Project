package Dao;

import Dao.custom.ChangeDao;
import Dao.custom.impl.ChangeDaoImpl;
import Dao.custom.impl.CustomerDaoImpl;
import Dao.custom.impl.ItemDaoImpl;
import Dao.custom.impl.RegisterDaoImpl;
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
        }
        return null;
    }
}



