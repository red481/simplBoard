package com.example.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    @Column(length = 10000)
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private EntityState entityState;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies;

    public Post() {
    }

    public Post(String title, String content, String author, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.replies = new ArrayList<>();
        this.entityState = EntityState.CREATED;
    }

    public void addReply(Reply reply) {
        replies.add(reply);
        reply.setPost(this);
    }

    public void removePost() {
        this.replies.forEach(reply -> reply.removeReply());
        this.entityState = EntityState.DELETED;
    }

    public void deleteReply(Long replyId){
        this.replies.stream().filter(reply -> reply.getReplyId().equals(replyId)).findFirst().get().removeReply();
    }
}
