package com.shesternev.jpa.service;

import com.shesternev.jpa.model.Item;
import com.shesternev.jpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService{

    private final ItemRepository itemRepository;

    @Override
    public void add(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item getById(long id) {
        return itemRepository.findById(id).orElseThrow();
    }
}
