package com.devpimentel.restapi.restapi.repository;

import com.devpimentel.restapi.restapi.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findByNameContaining(String name, Pageable pageable);
}
