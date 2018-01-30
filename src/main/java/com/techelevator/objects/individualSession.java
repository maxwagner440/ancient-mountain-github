package com.techelevator.objects;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class individualSession {

	private Long individualSessionId;
	private Long sessionId;
	private LocalDateTime dateBought;
	private LocalDateTime dateRedeemed;
	private BigDecimal cost;
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
	
	public String getBoughtDate() {
		return dateBought.getMonthValue() + "-" + dateBought.getDayOfMonth() + "-" + dateBought.getYear();
	}
	public String getTrueDate() {
		if(dateRedeemed != null) {
		return dateRedeemed.getMonthValue() + "-" + dateRedeemed.getDayOfMonth() + "-" + dateRedeemed.getYear();
		}
		return null;
	}
	
	public Long getIndividualSessionId() {
		return individualSessionId;
	}
	public void setIndividualSessionId(Long individualSessionId) {
		this.individualSessionId = individualSessionId;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public LocalDateTime getDateBought() {
		return dateBought;
	}
	public void setDateBought(LocalDateTime dateBought) {
		this.dateBought = dateBought;
	}
	public LocalDateTime getDateRedeemed() {
		return dateRedeemed;
	}
	public void setDateRedeemed(LocalDateTime dateRedeemed) {
		this.dateRedeemed = dateRedeemed;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}
