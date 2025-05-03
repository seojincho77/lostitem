package com.example.lostitem.repositories;

import com.example.lostitem.models.Post;
import com.example.lostitem.models.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lostitem.models.Users;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // 게시글을 타입별로 찾기 (lost 또는 found)
    List<Post> findByPostType(PostType postType);

    // 사용자 ID로 게시글 찾기
    List<Post> findByUserId(Integer userId);

    // 게시글 제목으로 찾기 (부분 일치)
    List<Post> findByTitleContaining(String keyword);

    List<Post> findByUser(Users user);
}
