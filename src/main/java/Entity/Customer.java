package Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();

    public Customer(String customerId, String name, String contactNo, String email) {
        this.customerId=customerId;
        this.name=name;
        this.contactNo=contactNo;
        this.email=email;
    }
}
