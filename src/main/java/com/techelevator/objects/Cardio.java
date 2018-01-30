package com.techelevator.objects;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class Cardio extends WorkOut {

	private BigDecimal caloriesBurnt;
	private int duration;
	private BigDecimal intensity;
	private String modality;
	
	public Cardio(){
		
	}
	public Cardio(BigDecimal caloriesBurnt, String modality) {
		this.caloriesBurnt = caloriesBurnt;
		this.modality = modality;
	}
	public BigDecimal getCaloriesBurnt() {
		return caloriesBurnt;
	}
	public void setCaloriesBurnt(BigDecimal caloriesBurnt) {
		this.caloriesBurnt = caloriesBurnt;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public BigDecimal getIntensity() {
		return intensity;
	}
	public void setIntensity(BigDecimal intensity) {
		this.intensity = intensity;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	

}
