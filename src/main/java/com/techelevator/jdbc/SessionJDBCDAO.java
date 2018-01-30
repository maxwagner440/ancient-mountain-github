package com.techelevator.jdbc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.objects.Client;
import com.techelevator.objects.Session;
import com.techelevator.objects.individualSession;
@Component
public class SessionJDBCDAO implements SessionDAO {

private JdbcTemplate jdbcTemplate;

@Autowired
ClientDAO clientDao;	

	@Autowired
	public SessionJDBCDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void buyMoreSessions(String firstName, String lastName, Integer amountBought, Long clientId, BigDecimal cost) {
			String sqlStatement = "INSERT INTO sessions (amount_bought, amount_used, client_id, cost, date_bought, first_name, last_name) VALUES(?, 0, ?, ?, NOW(), ?, ?) returning session_id";
			Long sessionId = jdbcTemplate.queryForObject(sqlStatement, Long.class, amountBought, clientId, cost, firstName, lastName);
			String sqlStatementTwo = "INSERT INTO individual_session (session_id, date_bought, date_redeemed, cost, first_name, last_name) VALUES(?, NOW(), null, ?, ?, ?)";
			for(int i = 0; i < amountBought; i++) {
				jdbcTemplate.update(sqlStatementTwo, sessionId, cost, firstName, lastName);
			}
			
			
	}
	
	@Override
	public void deleteSessions(Long clientId, Long sessionId) {
		String sqlState ="DELETE FROM individual_session WHERE session_id = ?";
		jdbcTemplate.update(sqlState, sessionId);
		String sqlStatement = "DELETE FROM sessions WHERE session_id = ? AND client_id = ?";
		jdbcTemplate.update(sqlStatement, sessionId, clientId);
	}

	@Override
	public void redeemSession(Long clientId, Long individualSessionId, Long sessionId) {
		String getAmount = "SELECT amount_used FROM sessions WHERE session_id = ? AND client_id = ?";
		Integer amountUsed = jdbcTemplate.queryForObject(getAmount, Integer.class, sessionId, clientId);
		Integer newAmount = amountUsed + 1;
		String sqlStatement = "UPDATE sessions SET amount_used = ? WHERE client_id = ? AND session_id = ?";
		jdbcTemplate.update(sqlStatement, newAmount, clientId, sessionId);
		String sqlStatementTwo = "UPDATE individual_session SET date_redeemed = NOW() WHERE individual_session_id = ?";
		jdbcTemplate.update(sqlStatementTwo, individualSessionId);
	}

	@Override
	public List<Session> howManySessionsLeft(Long clientId) {
		List<Session> sessionsLeft = new ArrayList<>();
		String sqlStatement = "SELECT * FROM sessions WHERE client_id = ? ORDER BY date_bought";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStatement, clientId);
		while(results.next()){
			sessionsLeft.add(mapRowToSession(results));
		}
		return sessionsLeft;
	}
	
	@Override 
	public Session getSessionFromId(Long sessionId) {
		String sql = "SELECT * FROM sessions WHERE session_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, sessionId);
		Session session = new Session();
		while(result.next()) {
			session = mapRowToSession(result);
		}
		return session;
	}

	public Session mapRowToSession(SqlRowSet result) {
		Session tempSession = new Session();
		tempSession.setSessionId(result.getLong("session_id"));
		tempSession.setClientId(result.getLong("client_id"));
		tempSession.setCost(result.getBigDecimal("cost"));
		tempSession.setDateBought(result.getTimestamp("date_bought").toLocalDateTime());
		tempSession.setAmountBought(result.getInt("amount_bought"));
		tempSession.setAmountUsed(result.getInt("amount_used"));
		tempSession.setFirstName(result.getString("first_name"));
		tempSession.setLastName(result.getString("last_name"));
		return tempSession;
	}
	@Override
	public List<individualSession> getIndividualSessions(Long sessionId) {
		List<individualSession> allInd = new ArrayList<>();
		String sqlStatement = "SELECT * FROM individual_session WHERE session_id = ? ORDER BY date_redeemed ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStatement, sessionId);
		while(results.next()) {
			allInd.add(mapRowToIndividualSession(results));
		}
		return allInd;
	}

	public individualSession mapRowToIndividualSession(SqlRowSet result) {
		individualSession temp = new individualSession();
		temp.setIndividualSessionId(result.getLong("individual_session_id"));
		temp.setSessionId(result.getLong("session_id"));
		temp.setDateBought(result.getTimestamp("date_bought").toLocalDateTime());
		temp.setFirstName(result.getString("first_name"));
		temp.setLastName(result.getString("last_name"));
		if(result.getTimestamp("date_redeemed") == null){
		}
		else {
		temp.setDateRedeemed(result.getTimestamp("date_redeemed").toLocalDateTime());
		}
		temp.setCost(result.getBigDecimal("cost"));
		return temp;
	}
	
	public Client mapRowToClient(SqlRowSet row) {
		Client entry = new Client();
		entry.setClientId(row.getLong("client_id"));
		entry.setAge((row.getBigDecimal("age")));
		entry.setHeight(row.getBigDecimal("height"));
		entry.setGoalWeightInLbs(row.getBigDecimal("weight_lbs"));
		entry.setGender(row.getString("gender"));
		entry.setUsername(row.getString("username"));
		entry.setFirstName(row.getString("first_name"));
		entry.setLastName(row.getString("last_name"));
		
		return entry;
	}
	
	@Override
	public List<Session> getAllClientSessions(){
		List<Session> session = new ArrayList<>();
		
		String sqlOne = "SELECT * FROM sessions";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlOne);
		while(result.next()) {
		session.add(mapRowToSession(result));
		}
		
		return session;
		
	}
}
