package com.shesternev.jpa.dto;

import com.shesternev.jpa.model.Item;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {

    protected ItemDto() {
    }

    public ItemDto(Item item) {
        id = item.getId();
        name = item.getName();
        price = item.getPrice();
        if (item.getBuyer() != null) {
            buyer = new UserDto(item.getBuyer());
        }
    }

    private long id;
    @NotNull
    private String name;
    @NotNull
    private long price;
    private UserDto buyer;

    public Item toItem() {
        return new Item(name, price);
    }
}
