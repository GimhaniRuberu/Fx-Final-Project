package dto.tm;

import lombok.*;

import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ItemTm {
    private String code;
    private String category;
    private String name;
}
