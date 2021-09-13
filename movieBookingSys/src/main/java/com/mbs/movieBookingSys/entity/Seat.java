package com.mbs.movieBookingSys.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;


@Entity
@Table(name = "seat")
public class Seat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long seatId;

	@Column(name = "seat_type")
	private String seatType;
	
	@Column(name = "seat_price")
	private long seatPrice;

	@Column(name = "seat_number")
	private int seatNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable=false)
    private Movie movie;
	
//	@OneToOne(mappedBy = "seat" ,cascade = CascadeType.ALL)
//	private Booking booking;
//	
	public Seat() {
		
	}

	public Seat(String seatType, int seatNumber, long seatPrice, Movie movie) {
		this.seatType = seatType;
		this.seatNumber = seatNumber;
		this.seatPrice = seatPrice;
		this.movie = movie;
	}


	


	public long getSeatId() {
		return seatId;
	}



	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}



	public String getSeatType() {
		return seatType;
	}



	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}



	public int getSeatNumber() {
		return seatNumber;
	}



	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}



	public Movie getMovie() {
		return movie;
	}



	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public long getSeatPrice() {
		return seatPrice;
	}



	public void setSeatPrice(long seatPrice) {
		this.seatPrice = seatPrice;
	}




}
