package com.dariopontes.todo_list.repository;

import com.dariopontes.todo_list.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
