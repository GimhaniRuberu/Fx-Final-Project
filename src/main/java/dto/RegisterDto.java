package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RegisterDto {

    private String email;
    private String contNo;
    private String jobRole;
    private String userName;
}

