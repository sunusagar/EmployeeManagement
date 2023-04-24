package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {
	@Autowired
	private EmpService service;
	@GetMapping("/")
	public String home(org.springframework.ui.Model m)
	{
		List<Employee> emp=service.getAllEmp();
		m.addAttribute("emp",emp);
		return"index";
	}
	@GetMapping("/addemp")
	public String addemp()
	{
		return "addemp";
	}
	@PostMapping("/register")
	public String EmpRegister(@ModelAttribute Employee e,HttpSession session)
	{
		System.out.println(e);
		service.AddEmp(e);
		session.setAttribute("msg", "employee add sucessfully....");
		return "redirect:/";
	} 
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,org.springframework.ui.Model m)
	{
		 Employee e=service.getEMpById(id);
		 m.addAttribute("emp",e);
		return "edit";
	}
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e,HttpSession session)
	{
		service.AddEmp(e);
		session.setAttribute("msg", "employee data update sucessfully");
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id,HttpSession session)
	{
		service.deleteemp(id);
		session.setAttribute("msg", "employee data delete sucessfully");
		return "redirect:/";
	}
}
