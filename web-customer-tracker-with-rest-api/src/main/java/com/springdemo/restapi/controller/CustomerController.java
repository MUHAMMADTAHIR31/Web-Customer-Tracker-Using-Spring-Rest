package com.springdemo.restapi.controller;

import com.springdemo.restapi.entity.Customer;
import com.springdemo.restapi.service.CustomerService;
import com.springdemo.restapi.view.InvoiceDataPdfExport;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
        
    @RequestMapping("/list")
    public String listCustomer(Model theModel) {
        
        //get Customer from DAO
        List<Customer> theCustomer = customerService.getCustomers();
        
        //add  the customer to model
        theModel.addAttribute("customers",theCustomer);
        
        //value returns
        return "list-customer";
    }
    
    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        
        Customer theCustomer = new Customer();
        
        theModel.addAttribute("customer",theCustomer);
        
        return "customer-form";
    }
    
    @RequestMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer,BindingResult result, Map<String, Object> model){
        
        if (result.hasErrors()) {
            return "customer-form";
        }
         
        //save customer
        customerService.saveCustomer(theCustomer);
        
        return "redirect:/customer/list";
    }
    
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){
        
        Customer theCustomer = customerService.updateCustomer(theId);
        
        theModel.addAttribute("customer",theCustomer);
        
        return "customer-form";
    }
    
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId){
        
        customerService.deleteCustomer(theId);
        
        return "redirect:/customer/list";
    }
    
    @GetMapping("/pdf")
    public ModelAndView exportToPdf() {
        
        ModelAndView mav = new ModelAndView();
        mav.setView(new InvoiceDataPdfExport());
      //read data from DB
        List<Customer> list= customerService.getCustomers();
        
      //send to pdfImpl class
        mav.addObject("list", list);
        return mav; 
    }
    
}
