package Bo.custom.impl;

import Bo.custom.RegisterBo;
import Dao.DaoFactory;
import Dao.custom.RegisterDao;
import Dao.util.DaoType;
import Entity.Register;
import dto.RegisterDto;

import java.sql.SQLException;

public class RegisterBoImpl implements RegisterBo {

    private RegisterDao registerDao = DaoFactory.getInstance().getDao(DaoType.REGISTER);

    @Override
    public boolean saveOrder(RegisterDto dto) throws SQLException, ClassNotFoundException {
        return registerDao.save(new Register(
                dto.getUserName(),
                dto.getEmail(),
                dto.getJobRole(),
                dto.getContNo()
        ));
    }


}
