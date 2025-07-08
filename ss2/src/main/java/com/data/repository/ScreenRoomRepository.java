package com.data.repository;

import com.data.entity.ScreenRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRoomRepository extends JpaRepository<ScreenRoom, Long> {
    List<ScreenRoom> findByNameContainingIgnoreCase(String name);
    List<ScreenRoom> findByTheaterId(Long theaterId);
}
