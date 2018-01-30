package com.techelevator.jdbc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Security.PasswordHasher;
import com.techelevator.objects.Client;

@Component
public class ClientJDBCDAO implements ClientDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;
	
	@Autowired
	public ClientJDBCDAO(DataSource dataSource, PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
	}
	
	@Override
	public List<Client> getAllClients() {
		List<Client> allEntries = new ArrayList<Client>();
		String sqlSelectCals = "SELECT * FROM client";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCals);
		while(results.next()){
			allEntries.add(mapRowToClient(results));
		}
		return allEntries;
	}

	
	public Client mapRowToClient(SqlRowSet row) {
		Client entry = new Client();
		entry.setAge((row.getBigDecimal("age")));
		entry.setHeight(row.getBigDecimal("height"));
		entry.setGoalWeightInLbs(row.getBigDecimal("goal_weight"));
		entry.setWeightInLbs(row.getBigDecimal("weight_lbs"));
		entry.setGender(row.getString("gender"));
		entry.setUsername(row.getString("username"));
		entry.setClientId(row.getLong("client_id"));
		entry.setFirstName(row.getString("first_name"));
		entry.setLastName(row.getString("last_name"));
		return entry;
	}
	
	
	@Override
	public Client getClientById(Long clientId) {
		String sqlSelect = "SELECT * FROM client WHERE client_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelect, clientId);
		Client client = new Client();
		if(result.next()) {
			client = mapRowToClient(result);
		}
			return client;
	}
		
	
	@Override
	public boolean isCorrectPassword(String username, String password) {
		String sqlSearchForUser = "SELECT * "+
			      "FROM client "+
			      "WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, username);
		
		if(results.next()) {
		String storedSalt = results.getString("salt_string");
		String storedPassword = results.getString("user_password");
		String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
		return storedPassword.equals(hashedPassword);
			}
		else {
			return false;
		}
	}

	@Override
	public void doCardio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String finishDay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getClientId(String username){
		Client client = new Client();
		String sqlSelectClient = "SELECT client_id FROM client WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectClient, username);
		client.setClientId(results.getLong("client_id"));
		
		return client.getClientId();
	}

	@Override
	public Client getClientByUsername(String username) {
		Client client = new Client();
		String sqlSelectClient = "SELECT * FROM client WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectClient, username);
		while(results.next()){
			client = mapRowToClient(results);
		}
		return client;
	}
	

	
	@Override
	public BigDecimal calculateCaloricNeeds(Client client){
			BigDecimal BMR = new BigDecimal(0);
			BigDecimal weightInKg = (BigDecimal) client.getWeightInLbs().divide(new BigDecimal("2.20"), BigDecimal.ROUND_HALF_EVEN).setScale(2);
			BigDecimal height = (BigDecimal) client.getHeight().multiply(new BigDecimal("2.54")).setScale(2);
			if(client.getGender().equals("female")){
				BigDecimal part1 = new BigDecimal("665.09");
				BigDecimal part2 = new BigDecimal("9.56").multiply(weightInKg);
				BigDecimal part3 = new BigDecimal("1.84").multiply(height);
				BigDecimal part4 = new BigDecimal("4.67").multiply(client.getAge());
				BMR = BMR.add(part1).add(part2).add(part3).add(part4);
		
			}
			else{
				BigDecimal part1 = new BigDecimal("66.47");
				BigDecimal part2 = new BigDecimal("13.75").multiply(weightInKg);
				BigDecimal part3 = new BigDecimal("5.00").multiply(height);
				BigDecimal part4 = new BigDecimal("6.75").multiply(client.getAge());
				BMR = BMR.add(part1).add(part2).add(part3).add(part4);
			}
		String update = "UPDATE client SET basal_metabolic_rate = ? WHERE client_id = ?";
		jdbcTemplate.update(update, BMR, client.getClientId());
		return BMR;
		
	}

	@Override
	public BigDecimal updateActivityLevel(Long clientId, BigDecimal num) {
		if(num == null) {
			num = new BigDecimal(1);
		}
		Map<BigDecimal, BigDecimal> level = new HashMap<>();
		level.put(new BigDecimal(1), new BigDecimal(1.2));
		level.put(new BigDecimal(2), new BigDecimal(1.375));
		level.put(new BigDecimal(3), new BigDecimal(1.55));
		level.put(new BigDecimal(4), new BigDecimal(1.725));
		level.put(new BigDecimal(5), new BigDecimal(1.9));
		
		
		String bmr = "SELECT basal_metabolic_rate FROM client WHERE client_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(bmr, clientId);
		BigDecimal BMR = new BigDecimal(0.00);
		while(result.next()) {
			BMR = result.getBigDecimal("basal_metabolic_rate");
		};
		BigDecimal newBMR = BMR.multiply(level.get(num));
		String update = "UPDATE client SET daily_cals_needed = ? WHERE client_id = ?";
		jdbcTemplate.update(update, newBMR, clientId);
		return newBMR;
	}
	
	
	@Override
	public void addUser(Client client, String password) {
	
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		jdbcTemplate.update("INSERT INTO client (first_name, last_name, user_password, gender, username, weight_lbs, height, age, salt_string, goal_weight) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", client.getFirstName(), client.getLastName(), hashedPassword, client.getGender(), client.getUsername(),
				client.getWeightInLbs(), client.getHeight(), client.getAge(), saltString, client.getGoalWeightInLbs());
	}

	@Override
	public void changeInfo(Long clientId, BigDecimal weightInLbs, BigDecimal goalWeightInLbs, BigDecimal age) {
		String sql = "UPDATE client SET age = ?, weight_lbs = ?, goal_weight = ? WHERE client_id = ?";
		jdbcTemplate.update(sql, age, weightInLbs, goalWeightInLbs, clientId);
	}
}
