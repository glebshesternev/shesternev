package com.shesternev.jpa.service;

import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void add(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}