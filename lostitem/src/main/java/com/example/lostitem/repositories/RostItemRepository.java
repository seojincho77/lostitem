package com.example.demo.repositories;

import com.example.demo.models.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Integer> {

    // 특정 게시글에 해당하는 분실물 찾기
    LostItem findByPostId(Integer postId);
}
