package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.CategoryDto;
import com.shesternev.jpa.dto.UserDto;
import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.model.User;
import java.util.List;

public interface CategoryService {

    void addCategory(Category category);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    void updateCategory(long id, Category category);

    void updateCategoryName(long id, String name);

    default CategoryDto convertCategoryToDto(Category category) {
        return new CategoryDto(category);
    }

    default Category convertDtoToCategory(CategoryDto categoryDto) {
        return categoryDto.toCategory();
    }
}
