package com.techelevator.objects;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MealLog {

	private Long mealLogId;
	private Long client_id;
	private Date date;
	private int mealNumber;
	private String meal;
	private int protein;
	private int carbs;
	private int fat;
	private int caloriesConsumed;

	public String getTrueDate() {
		SimpleDateFormat formater = new SimpleDateFormat("MM-dd-yyyy");
		String datestring = formater.format(this.date);
		return datestring;
	}
	
	public int calculateCals() {
		return (this.protein * 4) + (this.fat * 9) + (this.carbs * 4);
	}
	
	public Long getClient_id() {
		return client_id;
	}
	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}
	public int getMealNumber() {
		return mealNumber;
	}
	public void setMealNumber(int mealNumber) {
		this.mealNumber = mealNumber;
	}
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public int getProtein() {
		return protein;
	}
	public void setProtein(int protein) {
		this.protein = protein;
	}
	public int getCarbs() {
		return carbs;
	}
	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}
	public int getFat() {
		return fat;
	}
	public void setFat(int fat) {
		this.fat = fat;
	}
	public void setCaloriesConsumed(int caloriesConsumed) {
		this.caloriesConsumed = caloriesConsumed;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCaloriesConsumed() {
		return caloriesConsumed;
	}

	public Long getMealLogId() {
		return mealLogId;
	}

	public void setMealLogId(Long mealLogId) {
		this.mealLogId = mealLogId;
	}
	
	
	
	
}
