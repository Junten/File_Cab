package com.aws.codestar.projecttemplates.controller;


import com.aws.codestar.projecttemplates.model.File;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.service.UserService;
import com.aws.codestar.projecttemplates.service.FileService;
import com.aws.codestar.projecttemplates.validator.UserValidator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AppController {
	@Autowired
    private UserService userService;
	
	@Autowired
    private UserValidator userValidator;
	
	@Autowired
	private FileService fileService;
	
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value = { "/" , "/login"}, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("userForm", new User());
		return "signup";
	}
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
            return "signup";
        }

        userService.save(userForm);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
    public String file(
    		Model model,
    		HttpServletRequest request) {
		List<File> fileList = fileService.listFiles();
    	model.addAttribute("fileList", fileList);
		return "file";
    }
}