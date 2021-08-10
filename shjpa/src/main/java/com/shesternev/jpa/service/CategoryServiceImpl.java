package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.CategoryDto;
import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(CategoryDto categoryDto) {
        Category category = categoryDto.toCategory();
        categoryRepository.save(category);
        categoryDto.setId(category.getId());
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        return new CategoryDto(categoryRepository.findById(id)
                                                 .orElseThrow());
    }

    @Override
    @Transactional()
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(CategoryDto::new)
                                 .toList();
    }

    @Override
    public void updateCategory(long id, CategoryDto categoryDto) {
        Category category = categoryDto.toCategory();
        category.setId(id);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategoryName(long id, String name) {
        Category category = categoryRepository.findById(id)
                                              .orElseThrow();
        category.setName(name);
        categoryRepository.save(category);
    }
}