package com.amigoscode.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsByEmail(String email);
}
