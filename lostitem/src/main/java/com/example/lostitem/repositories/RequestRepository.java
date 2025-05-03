package com.example.demo.repositories;

import com.example.demo.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    // 게시글 ID로 요청 목록 가져오기
    List<Request> findByPostId(Integer postId);

    // 특정 사용자 ID로 요청 목록 가져오기
    List<Request> findByUserId(Integer userId);

    // 관리자 ID로 요청 목록 가져오기
    List<Request> findByAdminId(Integer adminId);
}
