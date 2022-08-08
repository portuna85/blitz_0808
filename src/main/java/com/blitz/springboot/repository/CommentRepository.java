package com.blitz.springboot.repository;

import com.blitz.springboot.domain.Comment;
import com.blitz.springboot.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentByPostsOrderById(Posts posts);
}
