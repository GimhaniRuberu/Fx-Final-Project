package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RegisterDto {
    private String userName;
    private String email;
    private String jobRole;
    private String contNo;
}
