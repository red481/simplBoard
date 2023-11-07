package com.example.board.controller;

import com.example.board.PostDto;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MainControllerTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MainController mainController;

    @Test
    void enrollPost() {
        //given
        ArrayList<PostDto> postDtoList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            postDtoList.add(new PostDto("title" + i, "content" + i, "author" + i));
        }

        //when
        for(PostDto postDto : postDtoList){
            mainController.enrollPost(postDto);
        }

        //then
        assertThat(boardRepository.findAll().size()).isEqualTo(10);
    }

    @Test
    void checkPosts() {
        //given
        ArrayList<PostDto> postDtoList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            postDtoList.add(new PostDto("title" + i, "content" + i, "author" + i));
        }

        //when
        for(PostDto postDto : postDtoList){
            mainController.enrollPost(postDto);
        }

//        //then
//        assertThat(mainController.checkPosts(0, 5).getContent().size()).isEqualTo(5);
//        assertThat(mainController.checkPosts(1, 5).getContent().size()).isEqualTo(5);
//        for(int i = 9; i >= 5; i--){
//            assertThat(mainController.checkPosts(0, 5).getContent().get(9 - i).getTitle()).isEqualTo("title" + i);
//        }
//        for(int i = 4; i >= 0; i--){
//            assertThat(mainController.checkPosts(1, 5).getContent().get(4 - i).getTitle()).isEqualTo("title" + i);
//        }
    }
}