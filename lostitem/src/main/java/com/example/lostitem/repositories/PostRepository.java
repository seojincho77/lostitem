package com.example.lostitem.repositories;

import com.example.lostitem.models.Post;
import com.example.lostitem.models.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.lostitem.models.Users;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

    List<Post> findByPostType(PostType postType);

    List<Post> findTop7ByOrderByCreatedAtDesc();

    Page<Post> findByPostType(PostType postType, Pageable pageable);

    // 사용자 ID로 게시글 찾기
    List<Post> findByUser_Id(Integer userId);

    List<Post> findByTitleContaining(String keyword);

    List<Post> findByUser(Users user);
}
