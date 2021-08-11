package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.CategoryDto;
import com.shesternev.jpa.dto.ItemDto;
import com.shesternev.jpa.dto.UserDto;
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
        CategoryDto category = categoryService.getCategoryById(categoryId);
        UserDto user = userService.getUserById(userId);
        ItemDto item = itemService.getItemById(itemId);
        category.getItemAddedBy().put(user, item);
        categoryService.updateCategory(categoryId, category);
    }

    @Override
    public void addItemBuyer(long itemId, long userId) {
        ItemDto item = itemService.getItemById(itemId);
        UserDto user = userService.getUserById(userId);
        item.setBuyer(user);
        itemService.updateItem(itemId, item);
    }
}
