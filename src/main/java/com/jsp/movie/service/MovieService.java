package com.jsp.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.movie.entity.Movie;
import com.jsp.movie.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	public String save(Movie movie, RedirectAttributes redirectAttributes) {
		movieRepository.save(movie);
		redirectAttributes.addFlashAttribute("successMessage", "Movie added successfully");
		return "redirect:/main";
	}

	public String getAllMovies(ModelMap map, RedirectAttributes redirectAttributes) {
		List<Movie> movies = movieRepository.findAll();
		if (movies.isEmpty()) {
			redirectAttributes.addFlashAttribute("failMessage", "No movies available. Please add movies first.");
			return "redirect:/main";
		} else {
			map.put("movies", movies);
			return "view-movies";
		}
	}
	
	public String deleteMovie(Integer id,RedirectAttributes redirectAttributes) {
		movieRepository.deleteById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Movie deleted successfully");
		return "redirect:/view-movies";
	}

	public String loadEditPage(Integer id, ModelMap map) {
		Movie movie = movieRepository.findById(id).get();
		map.put("movie", movie);
		return "edit-movie";
	}

	public String update(Movie movie, RedirectAttributes redirectAttributes) {
		movieRepository.save(movie);
		redirectAttributes.addFlashAttribute("successMessage", "Movie updated successfully");
		return "redirect:/view-movies";
	}

}
