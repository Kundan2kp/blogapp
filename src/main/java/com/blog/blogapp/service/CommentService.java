package com.blog.blogapp.service;

import com.blog.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment (Long postId,CommentDto commentDto);
    List<CommentDto> getCommentByPostId(long postId);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
}
