/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Administrator
 */
public class MessageList implements SessionAware{
    
    private Map session;
    
    @Override
    public void setSession(Map session){
        this.session = session;
    }
    
    public String execute()
    {
        return "SUCCESS";
    }    
}
