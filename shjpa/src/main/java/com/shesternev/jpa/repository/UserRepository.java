package com.shesternev.jpa.repository;

import com.shesternev.jpa.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
