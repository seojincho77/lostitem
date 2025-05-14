package com.example.lostitem.repositories;

import com.example.lostitem.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    // 사용자 이메일로 사용자 찾기
    Optional<Users> findByEmail(String userEmail);

    // 사용자 이름으로 사용자 찾기
    Optional<Users> findByUsername(String userName);

    Optional<Users> findByUserId(String userId);
}