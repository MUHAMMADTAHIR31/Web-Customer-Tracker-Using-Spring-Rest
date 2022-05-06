/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springdemo.restapi.rest;

import com.springdemo.restapi.entity.Customer;
import com.springdemo.restapi.entity.CustomerNotFoundException;
import com.springdemo.restapi.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
@RequestMapping("/api")
public class CustomerRestController {
    
    //autowired the Customerservice
    @Autowired
    private CustomerService customerService;
    
    //add mapping for get / customer
    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        
        return customerService.getCustomers();
    }
    
    // add mapping for Get  /Customers/{customerId}
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        
        Customer theCustomer = customerService.getCustomers(customerId);
        
        if(theCustomer == null){
            throw new CustomerNotFoundException("customer id not Found -"+ customerId);
        }
        return theCustomer;
    }
    
    // add mapping for post /customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer){
        
        //this save new Customer
        theCustomer.setId(0);
                
        customerService.saveCustomer(theCustomer);
                
        return theCustomer;
    } 
    
    //add mapping for put /customer - updating exits customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer){
        
        customerService.saveCustomer(theCustomer);
                
        return theCustomer;
    } 
    
    //Delete a customer /customer /{customerId} - delete the customer
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){
        
        Customer tempCustomer = customerService.getCustomers(customerId);
        
        if(tempCustomer == null){
            throw new CustomerNotFoundException("customer id not Found -"+ customerId);
        }
        
        customerService.deleteCustomer(customerId);
        
        return "Delete Customer Id -"+customerId;
    
    }
}
