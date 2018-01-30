package com.techelevator.objects;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {

	private Long sessionId;
	private Long clientId;
	private BigDecimal cost;
	private LocalDateTime dateBought;
	private int amountBought;
	private int amountUsed;
	private String firstName;
	private String lastName;

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
	public String getTrueDate() {
		return dateBought.getMonthValue() + "-" + dateBought.getDayOfMonth() + "-" + dateBought.getYear();
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public LocalDateTime getDateBought() {
		return dateBought;
	}
	public void setDateBought(LocalDateTime date) {
		this.dateBought = date;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public int getAmountBought() {
		return amountBought;
	}
	public void setAmountBought(int amountBought) {
		this.amountBought = amountBought;
	}
	public int getAmountUsed() {
		return amountUsed;
	}
	public void setAmountUsed(int amountUsed) {
		this.amountUsed = amountUsed;
	}
	
	
}
