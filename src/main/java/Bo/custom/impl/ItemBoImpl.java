package Bo.custom.impl;

import Bo.custom.ChangeBo;
import Bo.custom.ItemBo;
import Dao.DaoFactory;
import Dao.custom.ChangeDao;
import Dao.custom.ItemDao;
import Dao.util.DaoType;

import Entity.Item;
import dto.ChangeDto;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    private ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(
                dto.getCode(),
                dto.getCategory(),
                dto.getName()
        ));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(
                dto.getCode(),
                dto.getCategory(),
                dto.getName()
        ));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.delete(code);
    }

//    @Override
//    public boolean deleteItem(ItemDto dto) throws SQLException, ClassNotFoundException {
//        return itemDao.update(new Item(
//                dto.getCode(),
//                dto.getCategory(),
//                dto.getName()
//        ));
//    }

    @Override
    public List<ItemDto> allItem() throws SQLException, ClassNotFoundException {
        List<Item> entityList = itemDao.getAll();
        List<ItemDto> list = new ArrayList<>();
        for (Item item:entityList) {
            list.add(new ItemDto(
                    item.getItemCode(),
                    item.getCategory(),
                    item.getName()
            ));
        }
        return list;

    }
}
