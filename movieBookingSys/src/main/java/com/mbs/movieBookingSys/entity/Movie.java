package com.mbs.movieBookingSys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movieId;

	@Column(name = "movie_name")
	private String movieName;
	
	@Column(name = "movie_desc")
	private String movieDesc;
	
	@Column(name = "movie_show_time")
	private String movieShowTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie" ,cascade = CascadeType.ALL)
    private Set<Seat> seats;
	
	
	
	public Movie() {

	}

	public Movie(String movieName, String movieDesc, String movieShowTime) {
		this.movieName = movieName;
		this.movieDesc = movieDesc;
		this.movieShowTime = movieShowTime;
	}
	

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDesc() {
		return movieDesc;
	}

	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}

	public String getMovieShowTime() {
		return movieShowTime;
	}

	public void setMovieShowTime(String movieShowTime) {
		this.movieShowTime = movieShowTime;
	}
	
//	public List<Seat> getSeatList() {
//		return seatList;
//	}
//
//	public void setSeatList(List<Seat> seat) {
//		this.seatList = seat;
//	}
//
//	public Seat addSeat(Seat seat) {
//		getSeatList().add(seat);
//		seat.setMovie(this);
//		return seat;
//	}
//	
//	public Seat removeSeat(Seat seat) {
//		getSeatList().remove(seat);
//	    seat.setMovie(null);
//
//	    return seat;
//	}
	

}
