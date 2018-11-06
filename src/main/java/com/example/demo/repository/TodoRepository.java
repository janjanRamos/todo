package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	@Query("SELECT count(t.done) FROM Todo t where t.done = true GROUP BY t.done")
	public int quantityDone();
	
	@Query("SELECT count(t.done) FROM Todo t where t.done = false GROUP BY t.done")
	public int quantityUndone();

}
