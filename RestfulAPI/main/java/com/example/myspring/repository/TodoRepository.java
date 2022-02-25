package com.example.myspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspring.model.Todo;


public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findByTitleContaining(String title);
}
