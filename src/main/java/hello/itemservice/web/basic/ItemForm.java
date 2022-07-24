package hello.itemservice.web.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {
    private String name;
    private Integer price;
    private Integer quantity;
}
