package com.data.controller;

import com.data.entity.Movie;
import com.data.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final Map<Long, Movie> movieDatabase = new ConcurrentHashMap<>();
    private final Map<String, Integer> searchLogs = new ConcurrentHashMap<>();

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Movie movie) {
        try {
            movieRepository.save(movie);
            log.info("Phim '{}' đã được thêm thành công lúc {}", movie.getTitle(), LocalDateTime.now());
            return new ResponseEntity<>("Thêm phim thành công", HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Lỗi khi thêm phim: {}", ex.getMessage(), ex);
            return new ResponseEntity<>("Đã xảy ra lỗi khi thêm phim", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isEmpty()) {
                return new ResponseEntity<>("Không tìm thấy phim", HttpStatus.NOT_FOUND);
            }

            Movie oldMovie = optionalMovie.get();
            log.info("\033[33mThông tin phim cũ: {}\033[0m", oldMovie);

            updatedMovie.setId(id);
            movieRepository.save(updatedMovie);
            log.info("\033[32mThông tin phim đã cập nhật: {}\033[0m", updatedMovie);

            return new ResponseEntity<>("Cập nhật phim thành công", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("\033[31mLỗi khi cập nhật phim: {}\033[0m", ex.getMessage(), ex);
            return new ResponseEntity<>("Đã xảy ra lỗi khi cập nhật phim", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isEmpty()) {
                return new ResponseEntity<>("Không tìm thấy phim", HttpStatus.NOT_FOUND);
            }

            Movie deletedMovie = optionalMovie.get();
            movieRepository.deleteById(id);
            log.info("\033[31mXóa thành công:\033[0m \033[32m{}\033[0m", deletedMovie);

            return new ResponseEntity<>("Xóa phim thành công", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("\033[31mLỗi khi xóa phim: {}\033[0m", ex.getMessage(), ex);
            return new ResponseEntity<>("Đã xảy ra lỗi khi xóa phim", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> get(@RequestParam(required = false) String searchMovie) {
        try {
            List<Movie> result;
            if (searchMovie != null && !searchMovie.isEmpty()) {
                result = movieRepository.findAll().stream()
                        .filter(movie -> movie.getTitle().toLowerCase().contains(searchMovie.toLowerCase()))
                        .toList();

                log.info("\033[32mTừ khóa tìm kiếm: '{}', Kết quả: {}, Thời gian: {}\033[0m",
                        searchMovie, result.size(), LocalDateTime.now());
            } else {
                result = movieRepository.findAll();
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Lỗi khi lấy danh sách phim: {}", ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search-logs")
    public ResponseEntity<Map<String, Integer>> getHistory() {
        return new ResponseEntity<>(searchLogs, HttpStatus.OK);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<Movie>> suggest() {
        Set<String> keywords = searchLogs.keySet();
        List<Movie> suggestions = movieDatabase.values().stream()
                .filter(movie -> keywords.stream().anyMatch(keyword -> movie.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(suggestions, HttpStatus.OK);
    }
}
