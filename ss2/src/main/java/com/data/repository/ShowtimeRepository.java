package com.data.repository;

import com.data.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovieId(Long movieId);
    List<Showtime> findByScreenRoomId(Long screenRoomId);
    List<Showtime> findByMovie_Title(String title);
    List<Showtime> findByTheater_Name(String name);
    List<Showtime> findByMovie_TitleAndTheater_Name(String title, String name);
}
