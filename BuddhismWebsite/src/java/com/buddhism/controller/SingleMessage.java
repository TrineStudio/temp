/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.model.Administrator;
import com.buddhism.model.Message;
import com.buddhism.service.msgService;
import java.util.Date;
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
    
    private msgService service;
    
    

    public int getMessageIndex() {
        return messageIndex;
    }

    public void setMessageIndex(int messageIndex) {
        this.messageIndex = messageIndex;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

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
        if (messageIndex == -1)
            return "SUCCESS";
        else
        {
            Message msg = service.getMsg(messageIndex);
            
            messageContent = msg.getMessageContent();
            messageUrl = msg.getMessageLink();
            
            return "MODIFY";
        }
            
    }
    
    public String addMessage()
    {
        Message msg = new Message();
        
        msg.setMessageContent(messageContent);
        msg.setMessageDate(new Date());
        msg.setMessageLink(messageUrl);
        msg.setMessageAuthor(((Administrator)session.get("User")).getAdName());
        
        service.addMsg(msg);
        
        return "SUCCESS";
    }
    
    public String modifyMessage()
    {
        
        return "SUCCESS";
    }
    
}
