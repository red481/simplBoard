package com.example.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReplyId;
    @Column(length = 500)
    private String content;
    private String author;
    private EntityState entityState;
    private LocalDateTime createdDate;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Reply() {
    }

    public Reply(String Content, String author, LocalDateTime createdDate) {
        this.content = Content;
        this.author = author;
        this.createdDate = createdDate;
        this.entityState = EntityState.CREATED;
    }

    public Long getReplyId(){
        return this.ReplyId;
    }

    public void removeReply() {
        this.entityState = EntityState.DELETED;
    }
}
