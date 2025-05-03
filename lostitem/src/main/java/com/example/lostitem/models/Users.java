package com.example.lostitem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
////사용자 모델
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Boolean isAdmin;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Request> requests;

    @OneToMany(mappedBy = "admin")
    private List<Request> approvedRequests;
}
