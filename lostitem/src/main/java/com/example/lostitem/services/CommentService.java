package com.example.lostitem.services;

import com.example.lostitem.models.Comment;
import com.example.lostitem.models.Post;
import com.example.lostitem.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Repository는 데이터 입출구 (저장소)
//Service는 처리실 (데이터를 요리하는 곳)
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findByPostOrderByCreatedAtDesc(post);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Integer id) {
        return commentRepository.findById(id);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
