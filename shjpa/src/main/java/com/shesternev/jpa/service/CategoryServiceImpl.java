package com.shesternev.jpa.service;

import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                                 .orElseThrow();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(long id, Category category) {
        category.setId(id);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategoryName(long id, String name) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(name);
        categoryRepository.save(category);
    }
}