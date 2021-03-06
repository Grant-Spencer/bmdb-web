package com.bmdb.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmdb.business.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
List<Movie> findByDirector(String Director);
List<Movie> findByRating(String Rating);
	
}
