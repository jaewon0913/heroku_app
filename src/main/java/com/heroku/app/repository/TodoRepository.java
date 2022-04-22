package com.heroku.app.repository;

import com.heroku.app.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    // 작성
    public void save(Todo todo){
        em.persist(todo);
    }

    // 단건 조회
    public Todo findOne(Long id){
        return em.find(Todo.class, id);
    }

    // 전체 조회
    public List<Todo> findAll(){
        return em.createQuery("select t from Todo t", Todo.class).getResultList();
    }
}
