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
import com.mbs.movieBookingSys.repository.MovieRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/movie")
public class MovieController {
	
	@Autowired
	private MovieRepository repository;

	
	//Create a Movie //default 20 seats
	@RequestMapping(value = "/insertmovie", method = RequestMethod.POST, produces = "application/json") 
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		try {
	    	Movie mrepo = repository.save(new Movie(movie.getMovieName(), movie.getMovieDesc(), movie.getMovieShowTime()));
	    	return new ResponseEntity<Movie>(mrepo, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	return new ResponseEntity<Movie>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
//	@RequestMapping(value = "/insertmovieseats", method = RequestMethod.POST, produces = "application/json") 
//	public ResponseEntity<Movie> createMovieAndSeats(@RequestBody Movie movie) {
//		try {
//	    	Movie mrepo = repository.save(new Movie(movie.getMovieName(), movie.getMovieDesc(), movie.getMovieShowTime()));
//	    	return new ResponseEntity<Movie>(mrepo, HttpStatus.CREATED);
//	    } catch (Exception e) {
//	    	return new ResponseEntity<Movie>(HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
	
	
	
	//Retrieve movie by all or name  
    @RequestMapping(value = "/getmovies", method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody 
    public ResponseEntity<?> getMovies(@RequestParam(required = false) String movieName){ 
    	List<Movie> movieList = new ArrayList<Movie>();
    	if (movieName == null) {
    		movieList.addAll(repository.findAll());
    	}
    	else {
    		movieList.addAll(repository.findByMovieNameContaining(movieName));
//    		repository.findByMovieNameContaining(movieName).forEach(movieList::add);
    	}
        return new ResponseEntity<>(movieList,HttpStatus.OK); 
    }
    
    @RequestMapping(value = "/getmoviesbyid", method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody 
    public ResponseEntity<?> getMoviesById(@RequestParam(required = true) long movieId){ 
    	Optional<Movie> m = repository.findById(movieId);
    	if (m.isPresent()) {
    		Movie movieObj = m.get();
		}
        return new ResponseEntity<>(m,HttpStatus.OK); 
    }
	
    
    //Update movie by id
    @RequestMapping(value = "/updatemovie/{id}", method = RequestMethod.POST, produces = "application/json") 
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") long movieId, @RequestBody Movie movie) {
    	Optional<Movie> m = repository.findById(movieId);
    	if (m.isPresent()) {
    		Movie movieObj = m.get();
    		movieObj.setMovieName(movie.getMovieName());
    		movieObj.setMovieDesc(movie.getMovieDesc());
    		movieObj.setMovieShowTime(movie.getMovieShowTime());
    		return new ResponseEntity<>(repository.save(movieObj), HttpStatus.OK);
    	} 
    	else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
    //Delete movie by id
    @RequestMapping(value = "/deletemovie/{id}", method = RequestMethod.DELETE, produces = "application/json") 
    public ResponseEntity<?> deleteMovie(@PathVariable("id") long id) {
    	String response = "Movie ID : (" + id + ")";
    	try {
    		repository.deleteById(id);
    		response += " has been deleted successfully";
			return new ResponseEntity<>(response,HttpStatus.OK);
    	}catch (Exception e) {
    		response += " not deleted successfully";
    		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
	
	
}
