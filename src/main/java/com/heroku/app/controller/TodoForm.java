package com.heroku.app.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TodoForm {

    @NotEmpty(message = "제목은 필수입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수입니다.")
    private String content;
}
