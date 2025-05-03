package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lost_items")
public class LostItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lostId;

    private String lostPlace;

    private LocalDate lostDate;

    private Boolean isRecovered;

    private LocalDate createdAt;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
