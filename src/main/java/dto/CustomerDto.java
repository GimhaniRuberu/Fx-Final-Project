package dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String customerId;
    private String name;
    private String contactNo;
    private String email;
}
