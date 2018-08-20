package com.devpimentel.restapi.restapi.repository;

import com.devpimentel.restapi.restapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
