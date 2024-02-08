package Bo.custom.impl;

import Bo.custom.RegisterBo;
import Dao.DaoFactory;
import Dao.custom.RegisterDao;
import Dao.util.DaoType;
import Entity.Item;
import Entity.Register;
import dto.ItemDto;
import dto.RegisterDto;

import javax.persistence.Id;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<RegisterDto> allUsers() throws SQLException, ClassNotFoundException {
        List<Register> entityList = registerDao.getAll();
        List<RegisterDto> list = new ArrayList<>();
        for (Register register:entityList) {
            list.add(new RegisterDto(
                    register.getUserName(),
                    register.getEmail(),
                    register.getJobRole(),
                    register.getContNo()
            ));
        }
        return list;
    }
}
