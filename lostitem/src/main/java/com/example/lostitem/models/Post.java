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
    private Integer id;

    private String title;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType postType; // Enum 타입 정의 필요

    @ManyToOne
    @JoinColumn(name = "storage_location_id")
    private Locations storageLocation;

    @Column(name = "found_place")
    private String foundPlace;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    /*@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;*/

    @Column(name = "is_recovered")
    private Boolean isRecovered = false;

    @ManyToOne
    @JoinColumn(name = "recovered_by_admin_id")
    private Users recoveredByAdmin;

    @Column(name = "recovered_at")
    private LocalDateTime recoveredAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private LostItem lostItem;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    /*@OneToMany(mappedBy = "post")
    private List<Reward> rewards;*/
}