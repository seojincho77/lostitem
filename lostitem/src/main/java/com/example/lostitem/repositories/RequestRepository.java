package com.example.lostitem.repositories;

import com.example.lostitem.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    // 게시글 ID로 요청 목록 가져오기
    List<Request> findByPost_PostId(Integer postId);

    // 사용자 ID로 요청 목록 가져오기
    List<Request> findByUser_UserId(Integer userId);

    // 관리자 ID로 요청 목록 가져오기
    List<Request> findByAdmin_UserId(Integer adminId);

    // 요청자 ID로 요청 목록 가져오기 (필요시)
    List<Request> findByRequester_UserId(Integer requesterId);
}