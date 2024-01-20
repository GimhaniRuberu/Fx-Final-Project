package Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private String customerId;
    private String name;
    private String contactNo;
    private String email;
}
