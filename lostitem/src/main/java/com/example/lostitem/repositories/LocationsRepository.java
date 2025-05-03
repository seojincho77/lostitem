package com.example.lostitem.repositories;

import com.example.lostitem.models.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> {

    // 장소 이름으로 검색 (부분 일치 포함)
    List<Locations> findByLocNameContaining(String keyword);
}