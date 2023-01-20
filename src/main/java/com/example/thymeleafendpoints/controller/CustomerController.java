package com.example.thymeleafendpoints.controller;

import com.example.thymeleafendpoints.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final List<Customer> CUSTOMERS = new ArrayList<>();

    @GetMapping
    public String getCustomerView(Model model){
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers",CUSTOMERS);
        return "customers";
    }

    @PostMapping
    public String createCustomer(@Valid Customer customer, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "customers";
        }

        CUSTOMERS.add(customer);
        return "redirect:/customers";
    }
}
