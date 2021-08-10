package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.ItemDto;
import com.shesternev.jpa.dto.UserDto;
import com.shesternev.jpa.model.Item;
import com.shesternev.jpa.model.User;
import com.shesternev.jpa.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public void addItem(ItemDto itemDto) {
        Item item = itemDto.toItem();
        itemRepository.save(item);
        itemDto.setId(item.getId());
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll()
                             .stream()
                             .map(ItemDto::new)
                             .toList();
    }

    @Override
    public ItemDto getItemById(long id) {
        return new ItemDto(itemRepository.findById(id)
                                         .orElseThrow());
    }

    @Override
    public void updateItem(long id, ItemDto itemDto) {
        Item item = itemDto.toItem();
        itemRepository.save(item);
    }

    @Override
    public void updateItemNameAndPrice(long id, String name, long price) {
        Item item = itemRepository.findById(id)
                                  .orElseThrow();
        item.setName(name);
        item.setPrice(price);
        itemRepository.save(item);
    }

    @Override
    public void setItemBuyer(long id, UserDto user) {
        Item item = itemRepository.findById(id)
                                  .orElseThrow();
        item.setBuyer(user.toUser());
        itemRepository.save(item);
    }
}
