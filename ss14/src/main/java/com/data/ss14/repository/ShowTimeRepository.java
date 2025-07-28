package com.data.ss14.repository;

import com.data.ss14.model.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long>{
    boolean existsByMovieId(Long movieId);
}
