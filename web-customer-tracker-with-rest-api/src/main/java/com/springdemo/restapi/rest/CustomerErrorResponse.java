/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springdemo.restapi.rest;

/**
 *
 * @author Dell
 */
public class CustomerErrorResponse {
    
       private int status;
    private String messagge;
    private long timeStamp;

    public CustomerErrorResponse() {
    }

    public CustomerErrorResponse(int status, String messagge, long timeStamp) {
        this.status = status;
        this.messagge = messagge;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessagge() {
        return messagge;
    }

    public void setMessagge(String messagge) {
        this.messagge = messagge;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
}
