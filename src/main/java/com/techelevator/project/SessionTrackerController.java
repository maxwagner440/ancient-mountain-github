package com.techelevator.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.jdbc.CaloriesInputDAO;
import com.techelevator.jdbc.ClientDAO;
import com.techelevator.jdbc.SessionDAO;
import com.techelevator.objects.Client;
import com.techelevator.objects.Session;
import com.techelevator.objects.individualSession;

@Controller
public class SessionTrackerController {
	
	@Autowired
	ClientDAO clientDao;
	
	@Autowired
	CaloriesInputDAO caloriesDao;
	
	@Autowired
	SessionDAO sessionDao;
	
	@RequestMapping(path="/sessionTrackerLogin", method=RequestMethod.GET)
	public String displaySessionTrackerLogin(ModelMap modelHolder, HttpSession session){
		if(! modelHolder.containsAttribute("client")){
			modelHolder.put("client", new Client());
		}
		
		if(session.getAttribute("client") != null){
			Client client = (Client) session.getAttribute("client");
			session.setAttribute("client", client);
			return "redirect:/sTracker";
		}
		String message = (String) session.getAttribute("message");
		modelHolder.addAttribute("message", message);
		return "sessionTrackerLogin";
	}
	
	@RequestMapping(path="/sessionTrackerLogin", method=RequestMethod.POST)
	public String signInForClients(@RequestParam String username, @RequestParam String password,  HttpSession session, ModelMap modelHolder){
		if((username.equals("maxwagner440@gmail.com") ) && (clientDao.isCorrectPassword(username, password))) {
			session.setAttribute("message", null);
			Client client = clientDao.getClientByUsername(username);
			session.setAttribute("client", client);
			return "redirect:/pageAdmin";
		}
		else if(clientDao.isCorrectPassword(username, password)){
			session.setAttribute("message", null);
			Client client = clientDao.getClientByUsername(username);
			session.setAttribute("client", client);
			return "redirect:/clientDashboard";
		}
		else{
			session.setAttribute("message", "Incorrect Username or Password");
			return "redirect:/sessionTrackerLogin";
			
			
		}
		
		
		
	}
	
	@RequestMapping(path="/sTracker", method=RequestMethod.GET)
	public String displaySessionTracker(ModelMap modelHolder, HttpSession session){
		Client client = (Client) session.getAttribute("client");
		
		if(session.getAttribute("client") == null){
			return "redirect:/sessionTrackerLogin";
		}
		if(client.getUsername().equals("maxwagner440@gmail.com") ) {
			
			return "redirect:/allSessions";
		}
		
		List<Session> sessions = new ArrayList<>();
		sessions = sessionDao.howManySessionsLeft(client.getClientId());
		
		modelHolder.addAttribute("sessions", sessions);
		modelHolder.addAttribute("client", client);
		return "sTracker";
	}
	
	@RequestMapping(path="/sTracker", method=RequestMethod.POST)
	public String PostFromSessionTracker(@RequestParam Long sessionId, @RequestParam Long clientId, ModelMap modelHolder, HttpSession session){
		session.setAttribute("sessionId", sessionId);
		session.setAttribute("clientId", clientId);
		return "redirect:/individualSessions";
	}
	
	
	@RequestMapping(path="/individualSessions", method=RequestMethod.GET)
	public String gotoIndividualPurchases(ModelMap modelHolder, HttpSession session) {
		Long id = (Long)session.getAttribute("sessionId");
		List<individualSession> ourList = sessionDao.getIndividualSessions(id);
		Long clientId = (Long)session.getAttribute("clientId");
		modelHolder.addAttribute("individualSessions", ourList);
		modelHolder.addAttribute("clientId", clientId);
		return "individualSessions";
	}
	
	@RequestMapping(path="/individualSessions", method=RequestMethod.POST)
	public String redeemSession(ModelMap modelHolder, @RequestParam Long clientId, @RequestParam Long sessionId, @RequestParam Long individualSessionId) {
		sessionDao.redeemSession(clientId ,individualSessionId, sessionId);
		return "redirect:/individualSessions";
	}
	
	
	
	@RequestMapping(path="/allSessions", method=RequestMethod.GET)
	public String seeAllClients(HttpSession session, ModelMap modelHolder) {
		List<Client> allClients = clientDao.getAllClients();
		modelHolder.addAttribute("allClients", allClients);
		modelHolder.addAttribute("allSessions", sessionDao.getAllClientSessions());
		
		return "allSessions";
	}
	
	@RequestMapping(path="/allSessions", method=RequestMethod.POST)
	public String goToAddMoreSessions(HttpSession session, @RequestParam Long sessionId, @RequestParam Long clientId) {
		Session newSession = sessionDao.getSessionFromId(sessionId);
		session.setAttribute("clientSession", newSession);
		return "redirect:/moreSessions";
	}
	
	@RequestMapping(path="/giveSessions", method=RequestMethod.POST)
	public String gotoGiveSessions(HttpSession session, @RequestParam Long clientId,  @RequestParam Integer amountBought, @RequestParam BigDecimal cost) {
		Client client = clientDao.getClientById(clientId);
		sessionDao.buyMoreSessions(client.getFirstName(), client.getLastName(), amountBought, clientId, cost);
		return "redirect:/allSessions";
	}
	
	
	@RequestMapping(path="/moreSessions", method=RequestMethod.GET)
	public String showAddingMoreSessions(HttpSession session, ModelMap modelHolder) {
		Session newSession = (Session) session.getAttribute("clientSession");
		modelHolder.addAttribute("clientSession", newSession);
		return "/moreSessions";
	}
	
	@RequestMapping(path="/moreSessions", method=RequestMethod.POST)
	public String addMoreSessions(HttpSession session, @RequestParam Integer amountBought, @RequestParam BigDecimal cost, @RequestParam Long clientId) {
		Client client = clientDao.getClientById(clientId);
		sessionDao.buyMoreSessions(client.getFirstName(), client.getLastName(), amountBought, clientId, cost);
		
		return "redirect:/allSessions";
	}

	@RequestMapping(path="/delete", method=RequestMethod.POST)
	public String deleteThisSession(@RequestParam Long clientId, @RequestParam Long sessionId) {
		sessionDao.deleteSessions(clientId, sessionId);
		return "redirect:/allSessions";
	}

}


