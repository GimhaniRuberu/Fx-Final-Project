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

    @Id
    private String email;
    private String contNo;
    private String jobRole;
    private String userName;


    @OneToOne(mappedBy = "register")
    @PrimaryKeyJoinColumn
    private ChangePw changePw;

    public Register(String userName, String email, String jobRole, String contNo) {
        this.email = email;
        this.contNo = contNo;
        this.jobRole = jobRole;
        this.userName = userName;
    }
}
