package Bo.custom;

import Bo.SuperBo;
import dto.RegisterDto;

import java.sql.SQLException;

public interface RegisterBo extends SuperBo {
    boolean saveOrder(RegisterDto dto) throws SQLException, ClassNotFoundException;


}
