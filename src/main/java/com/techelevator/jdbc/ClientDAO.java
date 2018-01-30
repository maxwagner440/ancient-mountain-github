package com.techelevator.jdbc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.techelevator.objects.Client;

public interface ClientDAO {


	public void doCardio();
	public String finishDay();
	public List<Client> getAllClients();
	public Client getClientByUsername(String username);
	public Long getClientId(String username);
	public BigDecimal calculateCaloricNeeds(Client client);
	public void addUser	(Client client, String password);
	boolean isCorrectPassword(String username, String password);
	Client getClientById(Long clientId);
	public void changeInfo(Long clientId, BigDecimal weightInLbs, BigDecimal goalWeightInLbs, BigDecimal age);
	public BigDecimal updateActivityLevel(Long ClientId, BigDecimal num);
	
	//
//	public BigDecimal calculateCaloricNeeds();
//	public List<Integer> getAllConsumedEntries();
//	public List<Integer> getAllNeededEntries();
}
