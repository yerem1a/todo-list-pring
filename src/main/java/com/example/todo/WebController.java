package com.example.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    private final TodoService todoService;
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    public WebController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String getTodos(Model model) {
        List<Todo> todos = todoService.getTodos();
        model.addAttribute("todos", todos);
        logger.info("Fetched all todos");
        return "todo-list";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam String title, @RequestParam String description) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todoService.addTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTodo(@RequestParam Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }
}
