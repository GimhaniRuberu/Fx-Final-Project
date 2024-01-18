package dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ChangeDto {
    private String email;
    private String password;
    private String confirm;

    public ChangeDto(String text, String text1, String text2) {
        this.email = text;
        this.password = text1;
        this.confirm = text2;
    }
}
