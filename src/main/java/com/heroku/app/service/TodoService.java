package com.heroku.app.service;

import com.heroku.app.domain.Todo;
import com.heroku.app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * 글 작성
     */
    @Transactional
    public Long save(Todo todo){
        todoRepository.save(todo);

        return todo.getId();
    }

    /**
     * 글 전체 조회
     */
    public List<Todo> findTodos(){
        return todoRepository.findAll();
    }

    /**
     * 글 단건 조회
     */
    public Todo findOne(Long todoId){
        return todoRepository.findOne(todoId);
    }
}
