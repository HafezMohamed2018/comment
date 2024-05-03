package com.darbuka.comment.service;

import com.darbuka.comment.model.Comment;
import com.darbuka.comment.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(String id, Comment updatedComment) {
        if (commentRepository.existsById(id)) {
            updatedComment.setId(id);
            return commentRepository.save(updatedComment);
        }
        return null;
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }
}