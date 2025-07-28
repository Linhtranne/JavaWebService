package com.data.ss14.repository;

import com.data.ss14.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    boolean existsByTitle(String title);
}
