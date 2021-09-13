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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;

	@Column(name = "booking_status")
	private String bookingStatus;
	
	@Column(name = "booking_time")
	private String bookingTime;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id",unique=true)
	private Seat seat;
	
	
	
	
	public Booking() {

	}


	public Booking(String bookingStatus, String bookingTime, Seat seat) {
		this.bookingStatus = bookingStatus;
		this.bookingTime = bookingTime;
		this.seat = seat;
	}


	public long getBookingId() {
		return bookingId;
	}



	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}



	public String getBookingStatus() {
		return bookingStatus;
	}



	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}



	public String getBookingTime() {
		return bookingTime;
	}



	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}



	public Seat getSeat() {
		return seat;
	}



	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	

}
