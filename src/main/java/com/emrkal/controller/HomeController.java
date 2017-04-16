package com.emrkal.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emrkal.datamodel.PieDTO;
import com.emrkal.enums.ToDoStatus;
import com.emrkal.model.Activity;
import com.emrkal.model.Comment;
import com.emrkal.service.ActivityService;
import com.emrkal.service.CommentService;
import com.emrkal.service.UserService;
import com.google.gson.Gson;

/**
 * Login sonrası redirect edilen home sayfasına ait controller sınıfıdır..
 * <p>
 * 
 * @see HomeController
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Controller
public class HomeController {

	@Autowired
	private ActivityService acitivityService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	/**
	 * Login sonrası user interface deki alanlar için gerekli datalar database
	 * den sorgulanarak map edilir.
	 * <p>
	 * 
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = { "/home", "/" })
	public String getHomePage(Model model) {
		Activity activity = Activity.create(Activity::new);
		activity.setStatus(ToDoStatus.TODO);
		com.emrkal.model.User user = getLoggedUser();
		model.addAttribute("passingTimeActivities", acitivityService.findAllByUserOrderByEndDateAsc(user));
		model.addAttribute("activity", activity);
		model.addAttribute("userName", user.getUsername());
		model.addAttribute("comment", new Comment());
		model.addAttribute("commentList", commentService.getAllComment());
		List<PieDTO> pieDataList = Arrays.asList(//
				new PieDTO(acitivityService.CountByStatus(ToDoStatus.TODO, user), "grey"), //
				new PieDTO(acitivityService.CountByStatus(ToDoStatus.INPROGRESS, user), "blue"), //
				new PieDTO(acitivityService.CountByStatus(ToDoStatus.DONE, user), "green"));
		model.addAttribute("pieData", new Gson().toJson(pieDataList));
		return "/home";
	}

	private com.emrkal.model.User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.emrkal.model.User userEnt = userService.getUserByUsername(user.getUsername());
		return userEnt;
	}

}
