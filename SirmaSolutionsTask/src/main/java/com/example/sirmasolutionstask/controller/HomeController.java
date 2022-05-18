package com.example.sirmasolutionstask.controller;

import com.example.sirmasolutionstask.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final EmployeeService employeeService;

   @ModelAttribute(name = "isTrue")
   public boolean isTrue(){
       return true;
   }

    @Autowired
    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }


    @PostMapping("/")
    public String getResult(String input, Model model, RedirectAttributes redirectAttributes) {
        List<String> lines = Arrays.stream(input.split("\\r\\n"))
                .collect(Collectors.toList());

        redirectAttributes
                .addFlashAttribute("result", employeeService.getPairOfEmployees(lines))
                .addFlashAttribute("isTrue", false);
        return "redirect:/";
    }


}
