package com.data.repository;

import com.data.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByNameContainingIgnoreCase(String name);
    List<Theater> findByAddressContainingIgnoreCase(String address);
}
