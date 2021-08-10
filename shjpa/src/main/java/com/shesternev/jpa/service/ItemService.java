package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.ItemDto;
import com.shesternev.jpa.dto.UserDto;
import java.util.List;

public interface ItemService {

    void addItem(ItemDto itemDto);

    List<ItemDto> getAllItems();

    ItemDto getItemById(long id);

    void updateItem(long id, ItemDto item);

    void updateItemNameAndPrice(long id, String name, long price);

    void setItemBuyer(long id, UserDto user);

}
