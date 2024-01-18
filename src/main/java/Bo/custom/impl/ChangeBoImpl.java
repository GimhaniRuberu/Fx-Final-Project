package Bo.custom.impl;

import Bo.custom.ChangeBo;
import Dao.DaoFactory;
import Dao.custom.ChangeDao;
import Dao.util.DaoType;
import Entity.ChangePw;
import dto.ChangeDto;

import java.sql.SQLException;

public class ChangeBoImpl implements ChangeBo {

    private ChangeDao changeDao = DaoFactory.getInstance().getDao(DaoType.CHANGEPW);

    @Override
    public boolean saveChange(ChangeDto dto) throws SQLException, ClassNotFoundException {
        return changeDao.save(new ChangePw(
                dto.getEmail(),
                dto.getPassword(),
                dto.getConfirm()
        ));
    }
}
