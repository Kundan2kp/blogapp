package com.blog.blogapp.repository;

import com.blog.blogapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
   List<Comment> findByPostId(long postId);
}
