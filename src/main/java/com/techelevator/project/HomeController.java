package com.techelevator.project;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.jdbc.CaloriesInputDAO;
import com.techelevator.jdbc.ClientDAO;
import com.techelevator.jdbc.SessionDAO;
import com.techelevator.objects.Client;

@Controller
public class HomeController {
	
	
	@Autowired
	ClientDAO clientDao;
	
	@Autowired
	CaloriesInputDAO caloriesDao;
	
	@Autowired
	SessionDAO sessionDao;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayGreeting(ModelMap modelHolder, HttpSession session) {
		
		if(session.getAttribute("client") == null){
			modelHolder.put("client", new Client());
			return "home";
		}
		
		Client client = (Client) session.getAttribute("client");
		modelHolder.addAttribute("client", client);
		return "home";
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public String userLogin(@Valid @RequestParam String username, @Valid @RequestParam String password,  
			ModelMap modelHolder, HttpSession session) {
		if(clientDao.isCorrectPassword(username, password)){
			Client client = clientDao.getClientByUsername(username);
			session.setAttribute("client", client);
			return "redirect:/";
		}
		else{
			return "redirect:/";
		}
	}

	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("client");
		session.removeAttribute("client");
		session.invalidate();
		return "redirect:/";
	}
	
}
