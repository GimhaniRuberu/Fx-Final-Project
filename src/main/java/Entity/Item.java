package Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Item {
    @Id
    private String itemCode;
    private String category;
    private String name;
//    private String des;
}
