package com.mbs.movieBookingSys.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mbs.movieBookingSys.entity.Movie;
import com.mbs.movieBookingSys.entity.Seat;

import net.minidev.json.JSONObject;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
	
	List<Seat> findAll();
	
	List<Seat> findByMovie(Movie movie);
	
	@Query(value="SELECT s.seat_id,seat_number,seat_price,seat_type,movie_id FROM seat s LEFT join booking b on s.seat_id = b.seat_id WHERE s.movie_id = ? AND b.booking_status = \"PENDING\"",nativeQuery=true)
	List<JSONObject> findAllPendingSeatbyMovieId(long movieId);
	
}