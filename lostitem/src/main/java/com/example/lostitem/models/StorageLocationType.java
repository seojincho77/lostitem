package com.example.lostitem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
public enum StorageLocationType {
    CENTER("학생지원센터"),
    SECURITY("경비실"),
    LIBRARY("도서관 안내데스크"),
    ETC("기타(습득자 소지 중)");

    private final String displayName;

    StorageLocationType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
