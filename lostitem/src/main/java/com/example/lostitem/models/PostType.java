package com.example.lostitem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
public enum PostType {
    lost, found
}