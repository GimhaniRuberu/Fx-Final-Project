package Bo.custom;

import Bo.SuperBo;
import dto.CustomerDto;
import dto.RegisterDto;

import java.sql.SQLException;
import java.util.List;

public interface RegisterBo extends SuperBo {
    boolean saveOrder(RegisterDto dto) throws SQLException, ClassNotFoundException;
    List<RegisterDto> allUsers() throws SQLException, ClassNotFoundException;

}
