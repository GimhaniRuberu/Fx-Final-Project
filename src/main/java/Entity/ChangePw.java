package Entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class ChangePw {
    @Id
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn
    Register register;
    private String password;
    private String confirm;

    public ChangePw(String email, String password, String confirm) {
        this.id=email;
        this.password = password;
        this.confirm = confirm;
    }
}
