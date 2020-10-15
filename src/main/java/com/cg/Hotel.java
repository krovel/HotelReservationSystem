package com.cg;

public class Hotel {
	private String hotel;
	private int regularCustomerWeekdayRate;
	private int regularCustomerWeekendRate;
	
	public Hotel(String hotel, int regularCustomerWeekdayRate, int regularCustomerWeekendRate) {
		this.setHotelName(hotel);
		this.setRegularCustomerWeekdayRate(regularCustomerWeekdayRate);
		this.setRegularCustomerWeekendRate(regularCustomerWeekendRate);
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

	public int getRegularCustomerWeekendRate() {
		return regularCustomerWeekendRate;
	}

	public void setRegularCustomerWeekendRate(int regularCustomerWeekendRate) {
		this.regularCustomerWeekendRate = regularCustomerWeekendRate;
	}
}