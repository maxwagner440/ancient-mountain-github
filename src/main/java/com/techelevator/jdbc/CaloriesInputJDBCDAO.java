package com.techelevator.jdbc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.objects.MealLog;

@Component
public class CaloriesInputJDBCDAO implements CaloriesInputDAO {

	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CaloriesInputJDBCDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<MealLog> getAllEntries() {
		List<MealLog> allEntries = new ArrayList<MealLog>();
		String sqlSelectCals = "SELECT * FROM meal_log";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCals);
		while(results.next()){
			allEntries.add(mapRowToMealLog(results));
		}
		return allEntries;
	}

	@Override
	public void saveNewEntry(MealLog input, Long clientId){
		String sqlInputEntry = "INSERT INTO meal_log (client_id, date, meal_number, meal, protein, carbs, fat, calories_consumed) "
				+ "VALUES (?, NOW(), ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInputEntry, clientId, input.getMealNumber(), input.getMeal(), input.getProtein(), input.getCarbs(), input.getFat(), input.getCaloriesConsumed());
	}
	
	public MealLog mapRowToMealLog(SqlRowSet row) {
		MealLog entry = new MealLog();
		entry.setMealLogId(row.getLong("meal_log_id"));
		entry.setClient_id(row.getLong("client_id"));
		entry.setMeal(row.getString("meal"));
		entry.setMealNumber(row.getInt("meal_number"));
		entry.setDate(row.getDate("date"));
		entry.setProtein(row.getInt("protein"));
		entry.setCarbs(row.getInt("carbs"));
		entry.setFat(row.getInt("fat"));
		entry.setCaloriesConsumed(row.getInt("calories_consumed"));
		return entry;
	}

	@Override
	public List<MealLog> getAllEntriesByUsername(String username) {
		List<MealLog> allClientEntries = new ArrayList<MealLog>();
		String sqlSelectCals = "SELECT * FROM meal_log WHERE client_id = (SELECT client_id FROM client WHERE username = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCals, username);
		while(results.next()){
			allClientEntries.add(mapRowToMealLog(results));
		}
		return allClientEntries;
		
	}
	
	@Override
	public List<MealLog> getAllEntriesByDate(Date date, Long clientId){
		List<MealLog> allClientEntries = new ArrayList<MealLog>();
		String sqlSelectCals = "SELECT * FROM meal_log WHERE client_id = ? AND date = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCals, clientId, date);
		while(results.next()){
			allClientEntries.add(mapRowToMealLog(results));
		}
		return allClientEntries;
	}
	
	@Override
	public void deleteMealLog(Long mealLogId) {
		String sql = "DELETE FROM meal_log WHERE meal_log_id = ?";
		jdbcTemplate.update(sql, mealLogId);
		
	}

	@Override
	public MealLog getMealLogById(Long mealLogId) {
		MealLog log = new MealLog();
		String sqlSelectCals = "SELECT * FROM meal_log WHERE meal_log_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCals, mealLogId);
		while(results.next()){
			log = mapRowToMealLog(results);
		}
		
		return log;
	}

	@Override
	public MealLog getTotalMacrosByDate(Date date, Long clientId) {
		List<MealLog> all = new ArrayList<MealLog>();
		String sqlSelectCals = "SELECT * FROM meal_log WHERE client_id = ? AND date = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCals, clientId, date);
		while(results.next()){
			all.add(mapRowToMealLog(results));
		}
		
		MealLog total = new MealLog();
		int protein = 0;
		int carbs = 0;
		int fat = 0;
		for(int i=0; i < all.size();i++) {
			carbs += all.get(i).getCarbs();
			protein += all.get(i).getProtein();
			fat += all.get(i).getFat();
		}
		total.setCarbs(carbs);
		total.setProtein(protein);
		total.setFat(fat);
		
		return total;
	}

	@Override
	public List<Integer> getMealOptionsLeft(Date date, Long clientId) {
		List<Integer> nums = new ArrayList<>();
		List<Integer> numsReturn = new ArrayList<>();
		String sqlSelectCals = "SELECT meal_number FROM meal_log WHERE client_id = ? AND date = ? ORDER BY meal_number ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCals, clientId, date);
		while(results.next()){
			nums.add(results.getInt("meal_number"));
		}
			
		for(int i = 6; i > nums.size(); i--){
			numsReturn.add(i);
		}	
		Collections.reverse(numsReturn);
			return numsReturn;
		
	}
}
