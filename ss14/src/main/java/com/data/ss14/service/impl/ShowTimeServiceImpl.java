package com.data.ss14.service.impl;

import com.data.ss14.model.dto.request.ShowTimeRequestDTO;
import com.data.ss14.model.entity.Movie;
import com.data.ss14.model.entity.ShowTime;
import com.data.ss14.repository.MovieRepository;
import com.data.ss14.repository.ShowTimeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.data.ss14.service.ShowTimeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {
    private final ShowTimeRepo showTimeRepo;
    private final MovieRepository movieRepository;

    @Override
    public List<ShowTime> getAllShowTimes(){
        return showTimeRepo.findAll();
    }

    @Override
    public ShowTime saveShowTime(ShowTimeRequestDTO showTimeRequestDTO){
        Movie movie = movieRepository.findById(showTimeRequestDTO.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phim " + showTimeRequestDTO.getMovieId()));
        if(showTimeRepo.existsByMovieId(showTimeRequestDTO.getMovieId())){
            throw new IllegalArgumentException("Phim đã được chiếu");
        }
        return showTimeRepo.save(ShowTime.builder()
                .movie(movie)
                .startTime(LocalDateTime.parse(showTimeRequestDTO.getStartTime()))
                .room(showTimeRequestDTO.getRoom())
                .build());
    }
}