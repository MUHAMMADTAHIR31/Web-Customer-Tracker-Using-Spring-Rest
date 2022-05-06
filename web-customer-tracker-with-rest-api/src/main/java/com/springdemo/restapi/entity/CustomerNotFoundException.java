/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springdemo.restapi.entity;

/**
 *
 * @author Dell
 */
public class CustomerNotFoundException  extends RuntimeException{

    public CustomerNotFoundException(String string) {
        super(string);
    }

    public CustomerNotFoundException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public CustomerNotFoundException(Throwable thrwbl) {
        super(thrwbl);
    }
}
