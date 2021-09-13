package com.mbs.movieBookingSys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.movieBookingSys.entity.Movie;
import com.mbs.movieBookingSys.entity.Seat;
import com.mbs.movieBookingSys.repository.MovieRepository;
import com.mbs.movieBookingSys.repository.SeatRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/seat")
public class SeatController {
	
	@Autowired
	private MovieRepository mrepository;
	@Autowired
	private SeatRepository srepository;
	
	
	//Create a Movie 
	//default value >>> seatNo:1, seatType:Normal, seatPrice:7
	@RequestMapping(value = "/addseat", method = RequestMethod.POST, produces = "application/json") 
	public ResponseEntity<?> createSeats(@RequestParam(name = "movieId") long movieId, @RequestParam(name = "seats") int seats) {
		try {
			Optional<Movie> m = mrepository.findById(movieId);
			Movie movieObj = null;
			if (m.isPresent()) {
	    		movieObj = m.get();
	    		movieId = movieObj.getMovieId(); 
			}
			System.out.println(movieId);
			System.out.println(movieObj.getMovieName());
			String seatType = "Normal";
			int seatNumber = 1;
			long seatPrice = 7;
			for(int i=0; i<seats;i++) {
				srepository.save(new Seat(seatType,seatNumber+i,seatPrice,movieObj));				
			}
			return new ResponseEntity<>(srepository, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	System.out.println();
	    	e.printStackTrace();
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	
	
	
	
	
	
	
	
}
