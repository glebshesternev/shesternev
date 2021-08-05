package com.shesternev.jpa.repository;

import com.shesternev.jpa.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
