package Bo.custom;

import Bo.SuperBo;
import dto.ChangeDto;
import dto.RegisterDto;

import java.sql.SQLException;

public interface ChangeBo extends SuperBo {
    boolean saveChange(ChangeDto dto) throws SQLException, ClassNotFoundException;


}
