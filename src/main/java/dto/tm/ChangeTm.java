package dto.tm;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ChangeTm {
    private String email;
    private String password;
    private String confirm;
}
