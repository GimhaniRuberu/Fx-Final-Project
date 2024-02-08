package dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import lombok.*;

import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor

public class ItemTm {
    private String code;
    private String category;
    private String name;
    private Button btn;
}
