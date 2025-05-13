package com.example.lostitem.repositories;

import com.example.lostitem.models.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Integer> {

    // 특정 게시글에 해당하는 분실물 찾기
    LostItem findByPost_Id(Integer postId);

}