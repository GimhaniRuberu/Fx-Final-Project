package dto.tm;

import javafx.scene.control.Button;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CustomerTm {
    private String customerId;
    private String name;
    private String contactNo;
    private String email;
    private Button btn;

}
