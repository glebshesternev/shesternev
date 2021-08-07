package com.shesternev.jpa.repository;

import com.shesternev.jpa.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
