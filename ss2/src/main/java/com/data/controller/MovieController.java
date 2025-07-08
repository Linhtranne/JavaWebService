package com.data.controller;

import com.data.entity.Movie;
import com.data.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @GetMapping
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "movie-list";
    }
    @GetMapping("/add")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie-add";
    }
    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.save(movie);
        return "redirect:/movies";
    }
    @GetMapping("/edit/{id}")
    public String showEditMovieForm(@PathVariable Long id, Model model) {
        Movie movie = movieService.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phim với ID: " + id));
        model.addAttribute("movie", movie);
        return "movie-edit";
    }
    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieService.update(movie);
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return "redirect:/movies";
    }
}