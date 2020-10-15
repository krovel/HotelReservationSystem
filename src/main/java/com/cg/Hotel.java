package com.cg;

public class Hotel {
	private String hotel;
	private int regularCustomerWeekdayRate;
	
	public Hotel(String hotel, int regularCustomerWeekdayRate) {
		this.hotel = hotel;
		this.regularCustomerWeekdayRate = regularCustomerWeekdayRate;
	}

	public String getHotelName() {
		return hotel;
	}

	public void setHotelName(String hotel) {
		this.hotel = hotel;
	}

	public int getRegularCustomerWeekdayRate() {
		return regularCustomerWeekdayRate;
	}

	public void setRegularCustomerWeekdayRate(int regularCustomerWeekdayRate) {
		this.regularCustomerWeekdayRate = regularCustomerWeekdayRate;
	}
}