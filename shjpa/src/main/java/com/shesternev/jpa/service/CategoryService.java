package com.shesternev.jpa.service;

import com.shesternev.jpa.model.Category;

public interface CategoryService {
    void add(Category category);
    Category getById(long id);
}
