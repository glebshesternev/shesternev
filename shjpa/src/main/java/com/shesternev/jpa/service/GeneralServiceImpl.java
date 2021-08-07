package com.shesternev.jpa.service;

import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.model.Item;
import com.shesternev.jpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ItemService itemService;

    @Override
    public void addItemToCategory(long categoryId, long userId, long itemId) {
        Category category = categoryService.getCategoryById(categoryId);
        User user = userService.getUserById(userId);
        Item item = itemService.getItemById(itemId);
        category.getItemAddedBy().put(user, item);
        categoryService.updateCategory(categoryId, category);
    }

    @Override
    public void addItemBuyer(long itemId, long userId) {
        Item item = itemService.getItemById(itemId);
        User user = userService.getUserById(userId);
        item.setBuyer(user);
        itemService.updateItem(itemId, item);
    }
}
