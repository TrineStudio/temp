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
public class SingleMessage implements SessionAware{
    
    private Map session;
    
    private int messageIndex = -1;
    private String messageContent;
    private String messageUrl;
    
    @Override
    public void setSession(Map session){
        this.session = session;
    }
    
    public String execute()
    {
        if (messageIndex == -1)
            return "SUCCESS";
        else
            return "MODIFY";
    }
    
    public String addMessage()
    {
        return "SUCCESS";
    }
    
    public String modifyMessage()
    {
        
        return "SUCCESS";
    }
    
}
