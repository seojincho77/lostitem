package com.example.demo.repositories;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // 특정 게시글(post)에 달린 댓글 목록 가져오기
    List<Comment> findByPost(Post post);

    // 게시글 기준으로 최신순 정렬 (createdAt 내림차순)
    List<Comment> findByPostOrderByCreatedAtDesc(Post post);
}
