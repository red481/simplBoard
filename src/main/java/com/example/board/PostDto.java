package com.example.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private String author;

}
