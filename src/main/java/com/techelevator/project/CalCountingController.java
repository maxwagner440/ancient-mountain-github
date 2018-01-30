package com.techelevator.project;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.jdbc.CaloriesInputDAO;
import com.techelevator.jdbc.ClientDAO;
import com.techelevator.jdbc.ClientJDBCDAO;
import com.techelevator.objects.MealLog;
import com.techelevator.objects.Client;




@Controller 
public class CalCountingController {

	
	@Autowired
	ClientDAO clientDao;
	
	@Autowired
	CaloriesInputDAO caloriesDao;
	

	
	@RequestMapping(path="/newLog", method=RequestMethod.GET)
	public String gotoNewLogEntry(ModelMap modelHolder, HttpSession session){
		if(! modelHolder.containsAttribute("newLog")){
			modelHolder.put("newLog", new MealLog());
		}
		if(session.getAttribute("client") == null){
			return "redirect:/";
		}
		Client client = (Client) session.getAttribute("client");
		Date date = new Date();
		List<Integer> mealsLeft = caloriesDao.getMealOptionsLeft(date, client.getClientId());
		modelHolder.put("mealsLeft", mealsLeft);
		return "newLog";
	}
	
	@RequestMapping(path="/newLog", method=RequestMethod.POST)
	public String createEntryInDatabase(@ModelAttribute MealLog mealLog, HttpSession session){
		Client client = (Client) session.getAttribute("client");
		
		mealLog.setCaloriesConsumed(mealLog.calculateCals());
		
//		input.setCaloriesNeeded(clientDao.calculateCaloricNeeds(client.getClientId()));
//		input.setCaloriesConsumed(caloriesConsumed);
		caloriesDao.saveNewEntry(mealLog, client.getClientId());
		return "redirect:/allLogs";
	}
	
	@RequestMapping(path="/createNewProfile", method=RequestMethod.GET)
	public String displayCreateNewProfile(ModelMap modelHolder, HttpSession session){
		if(! modelHolder.containsAttribute("client")){
			modelHolder.put("client", new Client());
		}

		
		return "createNewProfile";
	}
	
	@RequestMapping(path="/createNewProfile", method=RequestMethod.POST)
	public String creatingNewProfile(@ModelAttribute Client client, @RequestParam String password, BindingResult result, RedirectAttributes flash){
		flash.addFlashAttribute("client", client);

		if(result.hasErrors()){
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "client", result);
			return "redirect:/createNewProfile";
		}
		clientDao.addUser(client, password);
		
		return "redirect:/";
	}
	
	
	
	
	@RequestMapping(path="/allLogs", method=RequestMethod.GET)
		public String showAllLogs(ModelMap modelHolder, HttpSession session){
		if(session.getAttribute("client") == null){
			return "redirect:/";
		}
		Client client = (Client) session.getAttribute("client");
		Date tempDate = (Date) session.getAttribute("date");
		
		if(tempDate == null) {
			tempDate = new Date();
		}
		List<MealLog> input = caloriesDao.getAllEntriesByDate(tempDate , client.getClientId());
		
		MealLog total = caloriesDao.getTotalMacrosByDate(tempDate, client.getClientId());
		modelHolder.addAttribute("total", total);
		modelHolder.addAttribute("allInput", input);

		return "allLogs";
		}
	
	@RequestMapping(path="/logInfo", method=RequestMethod.GET)
	public String showLogInfoInDepth(HttpSession session, ModelMap modelHolder) {
		Long mealLogId = (Long) session.getAttribute("mealLogId");
		MealLog log = caloriesDao.getMealLogById(mealLogId);
		modelHolder.addAttribute("log", log);
		return "logInfo";
	}

	@RequestMapping(path="/toLog", method=RequestMethod.POST)
	public String gotoLogInfo(HttpSession session, @RequestParam Long mealLogId) {
		session.setAttribute("mealLogId", mealLogId);
		return "redirect:/logInfo";
	}
	@RequestMapping(path="/allLogs", method=RequestMethod.POST)
	public String showLogsByDate(HttpSession session, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		session.setAttribute("date", date);
		return "redirect:/allLogs";
	}
	
	@RequestMapping(path="/deleteLog", method=RequestMethod.POST)
	public String deleteThisLog(@RequestParam Long mealLogId) {
		caloriesDao.deleteMealLog(mealLogId);
		return "redirect:/allLogs";
	}
}
