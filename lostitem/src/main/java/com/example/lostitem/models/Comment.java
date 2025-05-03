package com.example.lostitem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
