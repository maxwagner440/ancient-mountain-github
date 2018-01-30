package com.techelevator.jdbc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.techelevator.objects.Client;
import com.techelevator.objects.Session;
import com.techelevator.objects.individualSession;

public interface SessionDAO {
	
	public void buyMoreSessions(String firstName, String lastName, Integer amountBought, Long clientId, BigDecimal cost);
	public void redeemSession(Long clientId, Long individualSessionId, Long sessionId);
	public List<Session> howManySessionsLeft(Long clietId);
	public List<individualSession> getIndividualSessions(Long sessionId);
	public List<Session> getAllClientSessions();
	public Session getSessionFromId(Long sessionId);
	public void deleteSessions(Long clientId, Long sessionId);
}
