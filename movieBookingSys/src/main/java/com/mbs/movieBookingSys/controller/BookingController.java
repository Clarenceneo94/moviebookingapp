package com.mbs.movieBookingSys.controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
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

import com.mbs.movieBookingSys.entity.Booking;
import com.mbs.movieBookingSys.entity.Movie;
import com.mbs.movieBookingSys.entity.Seat;
import com.mbs.movieBookingSys.repository.BookingRepository;
import com.mbs.movieBookingSys.repository.MovieRepository;
import com.mbs.movieBookingSys.repository.SeatRepository;

import net.minidev.json.JSONObject;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@RequestMapping(value = "/mbs")
public class BookingController {
	
	@Autowired
	private MovieRepository mrepository;

	@Autowired
	private SeatRepository srepository;

	@Autowired
	private BookingRepository brepository;

	
//	@RequestMapping(value = "/mbs/book/booking", method = RequestMethod.POST, consumes = "application/json") 
	@PostMapping(path= "/mbs/book/booking" , consumes="application/json")
	public ResponseEntity<?> createBookingForSeatId(@RequestBody(required = true) Map<String, Long> seatIds) {
		try {
//			Optional<Movie> m = mrepository.findById(movieId);
//	    	if (m.isPresent()) {
//	    		Movie movieObj = m.get();
//			}
			final HttpHeaders httpHeaders= new HttpHeaders();
		    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//	    	System.out.println("__________ " + seatIds);
	    	String bookingStatus = "PENDING";
	    	String bookingTime = "7pm";
//	    	System.out.println("__________ " + seatIds.values());
			
	    	for(long seatId:seatIds.values()) {
	    		//Check whether list of seatIds:Long  exist in Seats table
				if(seatId == srepository.findById(seatId).get().getSeatId()) {
					Seat s = srepository.getById(seatId);
					Booking b = new Booking(bookingStatus,bookingTime,s);
		    		brepository.save(b);
					
				}
	    	}
	    	return new ResponseEntity<>(brepository, HttpStatus.CREATED);
	    } catch (Exception e) {
//	    	e.getMessage();
	    	return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	//Retrieve all seats by movieId 
    @RequestMapping(value = "/book/movieseats", method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody 
    public ResponseEntity<?> getSeatsByMovieId(@RequestParam(required = true) long movieId,@RequestParam(required = false) String status){ 
    	List <Seat> seats = new ArrayList<>();
    	try {
			Optional<Movie> m = mrepository.findById(movieId);
	    	if (m.isPresent()) {
	    		Movie movieObj = m.get();
	    		seats = srepository.findByMovie(movieObj);
	    		
			}
	    	return new ResponseEntity<>(seats,HttpStatus.OK);
    	}
    	catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
    
    @RequestMapping(value = "/book/movieseats/pending", method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody 
    public ResponseEntity<?> getPendingSeatsByMovieId(@RequestParam(required = true) long movieId){ 
    	List <JSONObject> seats = new ArrayList<>();
    	try {
			Optional<Movie> m = mrepository.findById(movieId);
	    	if (m.isPresent()) {
	    		Movie movieObj = m.get();
	    		seats = srepository.findAllPendingSeatbyMovieId(movieObj.getMovieId());
			}
	    	return new ResponseEntity<>(seats,HttpStatus.OK);
    	}
    	catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
    
    
    @RequestMapping(value = "/mbs/movieseating", method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody 
    public ResponseEntity<?> getSeatsByMovieIdJSON(@RequestParam(required = true) long movieId){ 
    	List <Seat> seats = new ArrayList<>();
    	List <JSONObject> jsonList = new ArrayList<>();
    	try {
			Optional<Movie> m = mrepository.findById(movieId);
	    	if (m.isPresent()) {
	    		Movie movieObj = m.get();
	    		seats = srepository.findByMovie(movieObj);
	    		for(Seat s: seats) {
	    			JSONObject json = new JSONObject();
	    			json.put("Seat",s);
	    			Booking b = brepository.findBookingBySeat(s);
	    			if(b != null) {
	    				json.put("Booking",b);
	    			}
	    			jsonList.add(json);
	    		}
			}
//	    	Collections.sort(jsonList, new JSONComparator());

	    	return new ResponseEntity<>(jsonList,HttpStatus.OK);
    	}
    	catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
    
    

	
	
	
	
}
