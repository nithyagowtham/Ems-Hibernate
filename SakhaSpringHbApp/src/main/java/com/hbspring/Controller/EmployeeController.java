package com.hbspring.Controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hbspring.model.Employee;
import com.hbspring.service.EmployeeService;
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService service;
	@PostMapping("/add")
	public String addEmployee(@ModelAttribute Employee emp,Model m)
	{
		
		String msg="failed";
		if(service.saveEmployee(emp))
			msg="success";
		m.addAttribute("msg",msg);
		return "view.jsp";
	}
	@GetMapping("/employee")
	public String getEmployee(@RequestParam("id") int empId,Model m)
	{
		Employee emp=service.getEmployee(empId);
		m.addAttribute("emp",emp);
		return "viewemp.jsp";
	}
	@GetMapping("/employees")
	public String getAllEmployees(Model m) {
		List<Employee> empList=service.getAllEmployee();
		m.addAttribute("emplist",empList);
		return "viewall.jsp";
	}
	@PostMapping("/delete")
	public String deleteEmp(@RequestParam("empId") int empid,Model m)
	{
		String msg="failed";
		Employee emp=service.getEmployee(empid);
		if(service.deleteEmployee(emp))
			msg="success";
		m.addAttribute("msg",msg);
		return "view.jsp";
		
	}
}
