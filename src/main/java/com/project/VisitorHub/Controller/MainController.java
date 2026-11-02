package com.project.VisitorHub.Controller;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.VisitorHub.Repository.VisitorRepo;
import com.project.VisitorHub.dto.Visitordto;
import com.project.VisitorHub.model.Visitor;
@Controller
public class MainController {
	@Autowired
	private VisitorRepo visitorRepo;
	@GetMapping("/")
	public String ShowIndex(Model model) {
		List<Visitor> visitors = visitorRepo.findAll();
		model.addAttribute("visitors", visitors);
		return "index";
	}
	
	
	@GetMapping("/addvisitor")
	public String showAddVisitor(Model model)
	{
		Visitordto dto = new Visitordto();
		model.addAttribute("dto", dto); 
		return "addvisitor";
	}
	
	@PostMapping("/addvisitor")
	public String addVisitor(@ModelAttribute Visitordto dto) {
		try {
			Visitor visitor = new Visitor();
			
	        visitor.setName(dto.getName());
	        visitor.setEmail(dto.getEmail());          // Add this
	        visitor.setAddress(dto.getAddress());
	        visitor.setContactNo(dto.getContactNo());
	        visitor.setPurpose(dto.getPurpose());
	        visitor.setInTime(LocalDateTime.now());

	        visitorRepo.save(visitor);

	        System.out.println("Visitor Successfully saved");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return "redirect:/addvisitor";
		
		
	}
	@GetMapping("/deleteVisitor/{id}")
	public String deleteVisitor(@PathVariable long id) 
	{
		visitorRepo.deleteById(id);
		return "redirect:/"; 
		
	}
	@GetMapping("/updateTime/{id}")
	public String updateOutTime(@PathVariable("id") long id) {
		Visitor visitor = visitorRepo.findById(id).get();
		visitor.setOutTime(LocalDateTime.now());
		visitorRepo.save(visitor);
		return"redirect:/";
	}
}

