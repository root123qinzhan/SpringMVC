package com.imooc.ssh.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.ssh.entity.Person;


import com.imooc.ssh.service.PersonService;

@RequestMapping(value="/person")
@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/login")
	public String login(){
		
		return "login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(String userName,String password){
		if(Objects.equals(userName, "admin")&&Objects.equals(password,"admin")){
			return "redirect:main";
		}
		return"redirt:login";
	}
	
	@RequestMapping("/main")
	public String main(Model model){
		model.addAttribute("persons",this.personService.getPersons());
		return "main";
	}
	
	@RequestMapping(value="/addPrompt")
	public String addPrompt(){
		return "addPerson";
	}
	
	@RequestMapping(value="/updatePrompt")
	public String updatePrompt(Model model,String id){
		model.addAttribute("person", this.personService.getPersonById(id));
		return "updatePerson";
	}

	@RequestMapping(value="/getPersons")
	@ResponseBody
	public List<Person> getPersons(){
		return personService.getPersons();
	}
	
	@RequestMapping(value="/getPersonById")
	@ResponseBody
	public Person getPersonById(String id){
		return personService.getPersonById(id);
	}

	@RequestMapping(value="/addPerson")
	public String addPerson(Person person){
		personService.addPerson(person);
		return "redirect:main"; 
	}
	
	@RequestMapping(value="/updatePerson")
    public String updatePerson(Person person){
		personService.updatePerson(person);
		return "redirect:main";
	}

	@RequestMapping(value="/deletePersonById")
    public String deletePersonById(String id){
	    personService.deletePersonById(id);
	    return "redirect:main";
    }
}
