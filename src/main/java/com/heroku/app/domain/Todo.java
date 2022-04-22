package com.heroku.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    private String title;

    private String content;

    private LocalDateTime writeDate;
    private LocalDateTime updateDate;
}
