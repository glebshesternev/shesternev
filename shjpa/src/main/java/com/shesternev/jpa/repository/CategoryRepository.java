package com.shesternev.jpa.repository;

import com.shesternev.jpa.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
