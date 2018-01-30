package com.techelevator.project;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpSession;

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
public class DashboardController {

		
		
		@Autowired
		ClientDAO clientDao;
		
		@Autowired
		CaloriesInputDAO caloriesDao;
		
		@Autowired
		SessionDAO sessionDao;
		
		
		
		@RequestMapping(path="/clientDashboard", method=RequestMethod.GET)
		public String goToDashboard(HttpSession session, ModelMap modelHolder) {
			if(session.getAttribute("client") != null) {
			Client client = (Client) session.getAttribute("client");
			modelHolder.addAttribute("cals", clientDao.calculateCaloricNeeds(client).setScale(0, RoundingMode.CEILING));
			modelHolder.addAttribute("client", client);
			return "clientDashboard";
			}
			else {
				return "redirect:/sessionTrackerLogin";
			}
		}
		
		@RequestMapping(path="/workouts", method=RequestMethod.GET)
		public String goToClientWorkouts() {
			return "workouts";
		}
		
		@RequestMapping(path="/pageAdmin", method=RequestMethod.GET)
		public String pageAdminAccess() {
			return "pageAdmin";
		}
		
		@RequestMapping(path="/infoChange", method=RequestMethod.GET)
		public String showInfoChange() {
			return "infoChange";
		}
		
		@RequestMapping(path="/infoChange", method=RequestMethod.POST)
		public String changeInfo(HttpSession session, @RequestParam(required=false) BigDecimal weightInLbs, @RequestParam(required=false) BigDecimal goalWeightInLbs, @RequestParam(required=false) BigDecimal age) {
			Client client = (Client) session.getAttribute("client");
			if(age != null) {
				client.setAge(age);
			}
			if(weightInLbs != null) {
				client.setWeightInLbs(weightInLbs);
			}
			if(goalWeightInLbs != null) {
				client.setGoalWeightInLbs(goalWeightInLbs);
			}
			clientDao.changeInfo(client.getClientId(), client.getWeightInLbs(), client.getGoalWeightInLbs(), client.getAge());
			return "redirect:/clientDashboard";
		}
		
		
		@RequestMapping(path="/calorieCalc", method=RequestMethod.GET)
		public String seeCalorieCalculations(HttpSession session) {
			return "calorieCalc";
		}
		
		@RequestMapping(path="/calorieCalc", method=RequestMethod.POST)
		public String changeCalNeedes(HttpSession session, @RequestParam BigDecimal activityLevel) {
			Client client = (Client) session.getAttribute("client");
			clientDao.updateActivityLevel(client.getClientId(), activityLevel);
			return "redirect:/calorieCalc";
		}
		
}
