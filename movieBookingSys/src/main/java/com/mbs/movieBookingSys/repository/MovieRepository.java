package com.mbs.movieBookingSys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mbs.movieBookingSys.entity.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByMovieNameContaining(String title);
	
	List<Movie> findAll();
	
//	Movie findByMovieId(String);
	
}