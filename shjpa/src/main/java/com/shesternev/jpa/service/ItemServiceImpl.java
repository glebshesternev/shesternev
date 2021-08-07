package com.shesternev.jpa.service;

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
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(long id) {
        return itemRepository.findById(id)
                             .orElseThrow();
    }

    @Override
    public void updateItem(long id, Item item) {
        item.setId(id);
        itemRepository.save(item);
    }

    @Override
    public void updateItemNameAndPrice(long id, String name, long price) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.setName(name);
        item.setPrice(price);
        itemRepository.save(item);
    }

    @Override
    public void setItemBuyer(long id, User user) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.setBuyer(user);
        itemRepository.save(item);
    }
}
