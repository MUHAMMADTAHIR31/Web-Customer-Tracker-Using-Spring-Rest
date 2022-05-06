/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springdemo.restapi.service;

import com.springdemo.restapi.dao.CustomerDAO;
import com.springdemo.restapi.entity.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dell
 */
@Service
public class CustomerServiceImp implements CustomerService{

    @Autowired
    private CustomerDAO customerDAO;
        
    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer theCustomer) {
        customerDAO.saveCustomer(theCustomer);
    }
 
    @Override
    @Transactional
    public Customer updateCustomer(int theId) {
        return customerDAO.updateCustomer(theId);
    }

    @Override
    @Transactional
    public void deleteCustomer(int theId) {
        customerDAO.deleteCustomer(theId);
    }

    @Override
    @Transactional
    public Customer getCustomers(int customerId) {
        return  customerDAO.getCustomers(customerId);
    }
}
