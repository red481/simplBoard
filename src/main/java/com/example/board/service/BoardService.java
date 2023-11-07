package com.example.board.service;

import com.example.board.PostDto;
import com.example.board.domain.EntityState;
import com.example.board.domain.Post;
import com.example.board.domain.Reply;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public Long enrollPost(PostDto postDto){
        Post post = new Post(postDto.getTitle(), postDto.getContent(), postDto.getAuthor(), LocalDateTime.now(), LocalDateTime.now());
        Post savedPost = boardRepository.save(post);
        return savedPost.getPostId();
    }
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    public void modifyPost(){

    }

    public Page<Post> checkPosts(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return boardRepository.findByEntityStateNot(EntityState.DELETED, pageable);
    }

    public Post findPostById(Long Id){
        Post foundedPost = boardRepository.findById(Id).get();
        return foundedPost;
    }

    public void enrollReply(Long id, Reply reply){
        Post post = boardRepository.findById(id).get();
        reply.setPost(post);
        post.addReply(reply);
        replyRepository.save(reply);
    }

    public void deleteReply(Long postId, Long replyId){
        findPostById(postId).deleteReply(replyId);
    }
}
