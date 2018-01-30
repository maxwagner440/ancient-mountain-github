package com.techelevator.objects;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Client {

	private Long clientId;
	
	@NotNull(message="must input a weight in lbs")
	private BigDecimal weightInLbs;
	
	@NotNull(message="must input a weight in lbs")
	private BigDecimal goalWeightInLbs;
	
	@NotNull(message="must input a height in inches")
	private BigDecimal height;
	
	@NotNull(message="must input an age")
	private BigDecimal age;
	
	@NotNull(message="must choose a gender")
	private String gender;
	
	@NotBlank(message="must fill out first name")
	private String firstName;
	
	@NotBlank(message="must fill out last name")
	private String lastName;
	
	@NotBlank(message="must fill out email") @Email(message="email is required")
	private String username;
	

		
	public Client(){
	}
	
	

	
	
	
	public BigDecimal getWeightInLbs() {
		return weightInLbs.setScale(1, RoundingMode.CEILING);
	}

	public void setWeightInLbs(BigDecimal weightInLbs) {
		this.weightInLbs = weightInLbs;
	}

	public BigDecimal getHeight() {
		return height.setScale(0, RoundingMode.CEILING);
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getAge() {
		return age.setScale(0, RoundingMode.CEILING);
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public BigDecimal getGoalWeightInLbs() {
		return goalWeightInLbs.setScale(1, RoundingMode.CEILING);
	}

	public void setGoalWeightInLbs(BigDecimal goalWeightInLbs) {
		this.goalWeightInLbs = goalWeightInLbs;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}



}
