package com.example.board;

import com.example.board.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse {
    private String title;
    private String content;
    private Long postId;
    private List<Reply> replies;
}
