package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RegisterTm {
    private String userName;
    private String email;
    private String jobRole;
    private String contNo;
}
