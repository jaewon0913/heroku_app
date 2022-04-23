package com.heroku.app.controller;

import com.heroku.app.domain.Todo;
import com.heroku.app.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos/new")
    public String createForm(Model model){
        log.info("Get : Todos New Form");
        model.addAttribute("todoForm", new TodoForm());

        return "todos/createTodoForm";
    }

    @PostMapping("/todos/new")
    public String createTodo(@Valid TodoForm form, BindingResult bindingResult){
        log.info("Post : Todos New");

        return validation(form, bindingResult);
    }

    @PostMapping("/todos/save")
    public String createJsonTodo(@RequestBody @Valid TodoForm form, BindingResult bindingResult){
        log.info("Post : Members Save");

        return validation(form, bindingResult);
    }

    @GetMapping("/todos")
    public String list(Model model){
        log.info("Get : Todos List");

        model.addAttribute("todos", todoService.findTodos());

        return "todos/todoList";
    }

    private String validation(@Valid @RequestBody TodoForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "todos/createTodoForm";
        }

        Todo todo = new Todo();
        todo.setTitle(form.getTitle());
        todo.setContent(form.getContent());
        todo.setWriteDate(LocalDateTime.now());
        todo.setUpdateDate(LocalDateTime.now());

        todoService.save(todo);

        return "redirect:/";
    }
}
