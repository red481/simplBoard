package com.example.board.controller;

import com.example.board.ApiResponse;
import com.example.board.PostDto;
import com.example.board.domain.EntityState;
import com.example.board.domain.Post;
import com.example.board.domain.Reply;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class MainController {

    private final BoardService boardService;

    @PostMapping("/Post")
    @ResponseBody
    public Long enrollPost(@RequestBody PostDto postDto){
        Long savedPostId = boardService.enrollPost(postDto);
        return savedPostId;
    }

    @DeleteMapping("/Post/{Id}")
    public void deletePost(@PathVariable Long Id){
        boardService.deletePost(Id);
    }

    @GetMapping("/Posts")
    public List<Post> checkPosts(@RequestParam int page, @RequestParam int size){
        return boardService.checkPosts(page, size).getContent();
    }

    @GetMapping("/Post/{id}")
    public ApiResponse checkPost(@PathVariable Long id){
        Post searchedPost = boardService.findPostById(id);
        return makeApiResponse(searchedPost);
    }

    @PostMapping("/Post/{id}/Reply")
    public void enrollReply(@PathVariable Long id, @RequestBody PostDto reply){
        Reply repl = new Reply(reply.getContent(), reply.getAuthor(), LocalDateTime.now());
        boardService.enrollReply(id, repl);

    }


    @DeleteMapping("/Post/{id}/Reply/{replyId}")
    public void deleteReply(@PathVariable Long id, @PathVariable Long replyId){
        boardService.deleteReply(id, replyId);
    }


    public ApiResponse makeApiResponse(Post post){
        List<Reply> repliesToReturn = post.getReplies() != null ? post.getReplies().stream()
                .filter(reply -> reply.getEntityState() != EntityState.DELETED)
                .collect(Collectors.toList()) : Collections.emptyList();

        return new ApiResponse(post.getTitle(), post.getContent(), post.getPostId(), repliesToReturn);
    }
}
