package dto;

import lombok.*;

import javax.persistence.Id;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String code;
    private String category;
    private String name;

}
