package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id){
        return todoRepository.findById(id);
    }

    public Todo addTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo updateTodoById(Long id, Todo todo) {
        return todoRepository.findById(id)
                .map(existingTodo -> {
                    existingTodo.setTitle(todo.getTitle());
                    existingTodo.setDescription(todo.getDescription());
                    return todoRepository.save(existingTodo);
                })
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }
}
