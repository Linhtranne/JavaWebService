package com.data.controller;

import com.data.entity.Movie;
import com.data.entity.ScreenRoom;
import com.data.entity.Showtime;
import com.data.service.MovieService;
import com.data.service.ScreenRoomService;
import com.data.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ScreenRoomService screenRoomService;
    @Autowired
    private ShowtimeService showtimeService;
    @GetMapping
    public String getAllShowtimes(@RequestParam(required = false) String movieTitle,
                                  @RequestParam(required = false) String theaterName,
                                  Model model) {
        List<Showtime> showtimes = showtimeService.filterShowtimes(movieTitle, theaterName);
        model.addAttribute("showtimes", showtimes);
        model.addAttribute("movieTitle", movieTitle);
        model.addAttribute("theaterName", theaterName);
        return "showtime-list";
    }
    @GetMapping("/add")
    public String showAddShowtimeForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "showtime-add";
    }
    @PostMapping("/add")
    public String addShowtime(@ModelAttribute Showtime showtime) {
        showtimeService.save(showtime);
        return "redirect:/showtimes";
    }
    @GetMapping("/edit/{id}")
    public String showEditShowtimeForm(@PathVariable Long id, Model model) {
        Showtime showtime = showtimeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Showtime not found with ID: " + id));
        List<Movie> movies = movieService.findAll();
        List<ScreenRoom> screenRooms = screenRoomService.findAll();
        System.out.println("Showtime: " + showtime);
        System.out.println("Movies: " + movies);
        System.out.println("ScreenRooms: " + screenRooms);

        model.addAttribute("showtime", showtime);
        model.addAttribute("movies", movies);
        model.addAttribute("screenRooms", screenRooms);
        return "showtime-edit";
    }
    @PostMapping("/edit/{id}")
    public String updateShowtime(@PathVariable Long id, @ModelAttribute Showtime showtime) {
        showtime.setId(id);
        showtimeService.update(showtime);
        return "redirect:/showtimes";
    }

    @PostMapping("/delete/{id}")
    public String deleteShowtime(@PathVariable Long id) {
        showtimeService.delete(id);
        return "redirect:/showtimes";
    }
}