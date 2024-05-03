package com.darbuka.comment.repositories;

import com.darbuka.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, String> {
}