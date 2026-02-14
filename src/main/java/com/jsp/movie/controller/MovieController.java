package com.jsp.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.movie.entity.Movie;
import com.jsp.movie.service.MovieService;

@Controller
public class MovieController {

	@Autowired
	MovieService movieService;

	@GetMapping({ "/", "/main" })
	public String loadMainPage() {
		return "main";
	}

	@GetMapping("/add-movie")
	public String loadAddMoviePage() {
		return "add-movie";
	}

	@PostMapping("/save-movie")
	public String saveMovie(Movie movie, RedirectAttributes redirectAttributes) {
		return movieService.save(movie, redirectAttributes);
	}

	@GetMapping("/view-movies")
	public String loadMovie(ModelMap map, RedirectAttributes redirectAttributes) {
		return movieService.getAllMovies(map, redirectAttributes);
	}

	@GetMapping("/delete/{id}")
	public String deleteMovie(@PathVariable Integer id, RedirectAttributes attributes) {
		return movieService.deleteMovie(id, attributes);
	}
	
	@GetMapping("/edit/{id}")
	public String loadEditPage(@PathVariable Integer id, ModelMap map) {
		return movieService.loadEditPage(id, map);
	}
	
	@PostMapping("/update-movie")
	public String updateMovie(Movie movie, RedirectAttributes redirectAttributes) {
		return movieService.update(movie, redirectAttributes);
	}
}
