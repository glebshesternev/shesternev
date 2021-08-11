package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    void addCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(long id);

    List<CategoryDto> getAllCategories();

    void updateCategory(long id, CategoryDto categoryDto);

    void updateCategoryName(long id, String name);
}
