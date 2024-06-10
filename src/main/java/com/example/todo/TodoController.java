package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable Long id){
        return todoService.getTodoById(id);
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodoById(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.updateTodoById(id, todo);
    }

}
