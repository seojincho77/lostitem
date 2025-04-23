package com.example.lostitem.repositories;

import com.example.lostitem.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}