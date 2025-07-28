package com.data.ss14.service.impl;

import com.data.ss14.model.dto.request.MovieRequestDTO;
import com.data.ss14.model.entity.Movie;
import com.data.ss14.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.data.ss14.service.MovieService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public Movie addMovie(MovieRequestDTO movieRequestDTO){
        if(movieRepository.existsByTitle(movieRequestDTO.getTitle())){
            throw new RuntimeException("Movie with title " + movieRequestDTO.getTitle() + " already exists");
        }
        return movieRepository.save(Movie.builder()
                .title(movieRequestDTO.getTitle())
                .description(movieRequestDTO.getDescription())
                .duration(movieRequestDTO.getDuration())
                .releaseDate(LocalDate.parse(movieRequestDTO.getReleaseDate()))
                .build());
    }

    @Override
    public Movie updateMovie(Long id, MovieRequestDTO movieRequestDTO){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with id " + id + " not found"));
        movie.setTitle(movieRequestDTO.getTitle());
        movie.setDescription(movieRequestDTO.getDescription());
        movie.setDuration(movieRequestDTO.getDuration());
        movie.setReleaseDate(LocalDate.parse(movieRequestDTO.getReleaseDate()));
        return movieRepository.save(movie);
    }


    @Override
    public void deleteMovie(Long id){
        if(!movieRepository.existsById(id)){
            throw new RuntimeException("Movie with id " + id + " not found");
        }
        movieRepository.deleteById(id);
    }
}