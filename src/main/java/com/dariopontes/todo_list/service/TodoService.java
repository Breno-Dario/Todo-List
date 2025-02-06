package com.dariopontes.todo_list.service;


import com.dariopontes.todo_list.entity.Todo;
import com.dariopontes.todo_list.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Todo todo) {
        if(!todoRepository.existsById(todo.getId())){
            throw new RuntimeException("Todos não encontrado para atualizar.");
        }

        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(Long id) {
        if(!todoRepository.existsById(id)){
            throw new RuntimeException("Todos não encontrado para exclusão.");
        }

        todoRepository.deleteById(id);
        return list();
    }
}
