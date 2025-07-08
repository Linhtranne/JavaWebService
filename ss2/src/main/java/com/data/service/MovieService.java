package com.data.service;
import com.data.entity.Movie;
import com.data.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IService<Movie, Long> {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie update(Movie movie) {
        if (movieRepository.existsById(movie.getId())) {
            return movieRepository.save(movie);
        }
        throw new IllegalArgumentException("Không tìm thấy phim với ID: " + movie.getId());
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}