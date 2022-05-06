/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springdemo.restapi.rest;


import com.springdemo.restapi.entity.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Dell
 */
@ControllerAdvice
public class CustomerRestExceptionHandler {
    
    //add exception handle code here
    
     //add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exce){
        
        // create a StudentErrorRespopnse
        CustomerErrorResponse errorResponse = new CustomerErrorResponse();
        
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessagge(exce.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        
        //return ResponseEntity
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    
    //add another exception handler .. to catch any exception
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception exce){
            
        // create a StudentErrorRespopnse
        CustomerErrorResponse errorResponse = new CustomerErrorResponse();
        
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessagge(exce.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        
        //return ResponseEntity
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }     
}
