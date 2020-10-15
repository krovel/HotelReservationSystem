package com.cg;

public class Hotel {
	private String hotel;
	private int regularCustomerWeekdayRate;
	private int regularCustomerWeekendRate;
	private int rating;
	private int rewardCustomerWeekdayRate;
	private int rewardCustomerWeekendRate;
	
	public Hotel(String hotel, int regularCustomerWeekdayRate, int regularCustomerWeekendRate, int rating,int rewardCustomerWeekdayRate, int rewardCustomerWeekendRate) {
		this.setHotelName(hotel);
		this.setRegularCustomerWeekdayRate(regularCustomerWeekdayRate);
		this.setRegularCustomerWeekendRate(regularCustomerWeekendRate);
		this.setRating(rating);
		this.setRewardCustomerWeekdayRate(rewardCustomerWeekdayRate);
		this.setRewardCustomerWeekendRate(rewardCustomerWeekendRate);
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
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getRewardCustomerWeekendRate() {
		return rewardCustomerWeekendRate;
	}

	public void setRewardCustomerWeekendRate(int rewardCustomerWeekendRate) {
		this.rewardCustomerWeekendRate = rewardCustomerWeekendRate;
	}

	public int getRewardCustomerWeekdayRate() {
		return rewardCustomerWeekdayRate;
	}

	public void setRewardCustomerWeekdayRate(int rewardCustomerWeekdayRate) {
		this.rewardCustomerWeekdayRate = rewardCustomerWeekdayRate;
	}
}