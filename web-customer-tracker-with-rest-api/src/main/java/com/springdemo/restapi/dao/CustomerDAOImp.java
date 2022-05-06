/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springdemo.restapi.dao;


import com.springdemo.restapi.entity.Customer;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dell
 */
@Repository  
public class CustomerDAOImp implements CustomerDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Customer> getCustomers() {
        //get current session
        Session currentSession = sessionFactory.getCurrentSession();
        //create Query
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName",Customer.class );
        //run the query
        List<Customer> custmer = query.getResultList();
        //return the result
        return custmer;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        //get current hibernate session
        Session currSession = sessionFactory.getCurrentSession();
        //save the customer---hb
        currSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer updateCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer theCustomer = currentSession.get(Customer.class, theId);
        return theCustomer;  
    }
    
    @Override
    public void deleteCustomer(int theId) {
        // gett the current session
        Session currentSession = sessionFactory.getCurrentSession();
        
        //delete object with primary key
        Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
            theQuery.setParameter("customerId", theId);
        
        //update/delete soo onn
        theQuery.executeUpdate();
    }

    @Override
    public Customer getCustomers(int theId) {

	// get the current hibernate session
	Session currentSession = sessionFactory.getCurrentSession();
		
	// now retrieve/read from database using the primary key
	Customer theCustomer = currentSession.get(Customer.class, theId);
		
        return theCustomer;
    }
}
