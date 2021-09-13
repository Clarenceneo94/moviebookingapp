package com.mbs.movieBookingSys.controller;

import java.util.Comparator;

import net.minidev.json.JSONObject;

public class JSONComparator implements Comparator<JSONObject> {



	@Override
	public int compare(JSONObject o1, JSONObject o2) {
	    String v1 = (String) ((JSONObject) o1.get("Seat")).get("seatId");
	    String v3 = (String) ((JSONObject) o2.get("Seat")).get("seatId");
	    return v1.compareTo(v3);
	}

}
