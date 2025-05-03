package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "locations")
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    private String locName;

    private Boolean isActive;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "location")
    private List<Post> posts;
}
