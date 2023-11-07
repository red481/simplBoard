package com.example.board.repository;

import com.example.board.domain.EntityState;
import com.example.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BoardRepository extends JpaRepository<Post, Long> {
    Page<Post> findByEntityStateNot(EntityState state, Pageable pageable);
}
