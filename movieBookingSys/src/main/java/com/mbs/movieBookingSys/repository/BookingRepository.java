package com.mbs.movieBookingSys.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mbs.movieBookingSys.entity.Booking;
import com.mbs.movieBookingSys.entity.Movie;
import com.mbs.movieBookingSys.entity.Seat;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	List<Booking> findAll();
	
	Booking findBookingBySeat(Seat seat);
	
//	List<Booking> findByMovieIdAndPendingStatus(long movieId, String status);
	
//	List<Booking> findAllByMovie(Movie movie);
}