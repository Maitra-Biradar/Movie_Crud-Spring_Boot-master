package com.jsp.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
