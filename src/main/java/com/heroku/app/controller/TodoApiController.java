package com.heroku.app.controller;

import com.heroku.app.domain.Todo;
import com.heroku.app.service.TodoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TodoApiController {

    private final TodoService todoService;

    @RequestMapping("/api/v1/todo")
    public List<ResponseTodoDto> getTodoList(){

        List<Todo> todos = todoService.findTodos(true);

        List<ResponseTodoDto> results = todos.stream()
                .map(o -> new ResponseTodoDto(o))
                .collect(Collectors.toList());

        return results;
    }

    @Data
    static class ResponseTodoDto {

        private Long id;
        private String item;
        private String date;
        private boolean completed;
        private String time;
        private LocalDateTime writeDate;

        public ResponseTodoDto(Todo todo){
            id = todo.getId();
            item = todo.getItem();
            date = todo.getDate();
            completed = todo.isCompleted();
            time = todo.getTime();
            writeDate = todo.getWriteDate();
        }
    }
}
