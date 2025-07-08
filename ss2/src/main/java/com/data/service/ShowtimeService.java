package com.data.service;

import com.data.entity.Showtime;
import com.data.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService implements IService<Showtime, Long> {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    @Override
    public Showtime save(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public Showtime update(Showtime showtime) {
        if (showtimeRepository.existsById(showtime.getId())) {
            return showtimeRepository.save(showtime);
        }
        throw new IllegalArgumentException("Không tìm thấy lịch chiếu với ID: " + showtime.getId());
    }

    @Override
    public void delete(Long id) {
        showtimeRepository.deleteById(id);
    }

    public List<Showtime> filterShowtimes(String movieTitle, String theaterName) {
        String movieTitleFilter = (movieTitle != null && !movieTitle.trim().isEmpty()) ? movieTitle.trim() : null;
        String theaterNameFilter = (theaterName != null && !theaterName.trim().isEmpty()) ? theaterName.trim() : null;

        if (movieTitleFilter != null && theaterNameFilter != null) {
            return showtimeRepository.findByMovie_TitleAndTheater_Name(movieTitleFilter, theaterNameFilter);
        } else if (movieTitleFilter != null) {
            return showtimeRepository.findByMovie_Title(movieTitleFilter);
        } else if (theaterNameFilter != null) {
            return showtimeRepository.findByTheater_Name(theaterNameFilter);
        } else {
            return showtimeRepository.findAll();
        }
    }
}