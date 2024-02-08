package dto;

import lombok.*;

import javax.persistence.Id;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String itemCode;
    private String category;
    private String name;
//    private String des;

    //private List<ItemDto> list;
}
