package Entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
//@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Register {
    private String userName;
    @Id
    private String email;
    private String jobRole;
    private String contNo;

    @OneToOne(mappedBy = "register")
    @PrimaryKeyJoinColumn
    private ChangePw changePw;

    public Register(String userName, String email, String jobRole, String contNo) {
        this.userName = userName;
        this.email = email;
        this.jobRole = jobRole;
        this.contNo = contNo;
    }
}
