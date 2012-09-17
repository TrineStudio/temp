/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.model.Message;
import com.buddhism.service.msgService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Administrator
 */
public class MessageList implements SessionAware{
    
    private Map session;
    
    private List<Message> messages = new ArrayList<Message>();
    
    private int messageID;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }
    
    private msgService service;

    public msgService getService() {
        return service;
    }

    public void setService(msgService service) {
        this.service = service;
    }
    
    
    
    @Override
    public void setSession(Map session){
        this.session = session;
    }
    
    public String execute()
    {
        
        
        return "SUCCESS";
    }   
    
    public String deleteMessage(int messageID)
    {
        
        service.deleteMsg(messageID);
        
        return "SUCCESS";
    }
}
