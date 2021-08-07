package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.ItemDto;
import com.shesternev.jpa.model.Item;
import com.shesternev.jpa.model.User;
import java.util.List;

public interface ItemService {

    void addItem(Item item);

    List<Item> getAllItems();

    Item getItemById(long id);

    void updateItem(long id, Item item);

    void updateItemNameAndPrice(long id, String name, long price);

    void setItemBuyer(long id, User user);

    default ItemDto convertItemToDto(Item item) {
        return new ItemDto(item);
    }

    default Item convertDtoToItem(ItemDto itemDto) {
        return itemDto.toItem();
    }
}
