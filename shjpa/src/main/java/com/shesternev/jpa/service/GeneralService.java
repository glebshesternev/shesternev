package com.shesternev.jpa.service;

import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.model.Item;
import com.shesternev.jpa.model.User;

public interface GeneralService {

    void addItemToCategory(long categoryId, long userId, long itemId);

    void addItemBuyer(long itemId, long userId);
}
