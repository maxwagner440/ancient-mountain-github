package com.techelevator.jdbc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.objects.MealLog;

public  interface CaloriesInputDAO {
	
	public List<MealLog> getAllEntries();
	public List<MealLog> getAllEntriesByUsername(String username) ;
	public void saveNewEntry(MealLog input, Long userId);
	public List<MealLog> getAllEntriesByDate(Date date, Long clientId);
	public void deleteMealLog(Long mealLogId);
	public MealLog getMealLogById(Long mealLogId);
	public MealLog getTotalMacrosByDate(Date date, Long clientId);
	public List<Integer> getMealOptionsLeft(Date date, Long clientId);
}
