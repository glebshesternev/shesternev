package com.shesternev.jpa.service;

import com.shesternev.jpa.model.Item;

public interface IItemService {
    void add(Item item);
    Item getById(long id);
}
