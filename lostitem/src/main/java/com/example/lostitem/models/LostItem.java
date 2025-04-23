package com.example.lostitem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//분실물 모델
//데이터베이스 테이블 구조 정의, 데이터베이스에 접근
@Entity
public class LostItem {
    @Id
    private Long id;
    private String title;
    private String description;
}
