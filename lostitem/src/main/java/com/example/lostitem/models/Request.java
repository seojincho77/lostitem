package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime reviewedAt;

    @Column(columnDefinition = "TEXT")
    private String thankYouMessage;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin; // 관리자

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester; // 요청자
}
