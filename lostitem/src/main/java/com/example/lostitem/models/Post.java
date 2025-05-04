package com.example.lostitem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PostType postType;  // ENUM('lost', 'found')

    private String storageLocationId;

    private String foundPlace;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "location_id2")
    private Locations location;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToOne(mappedBy = "post")
    private LostItem lostItem;

    @OneToMany(mappedBy = "post")
    private List<Request> requests;
}