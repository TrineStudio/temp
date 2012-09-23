/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.model.ActionPair;
import com.buddhism.model.Constants;
import com.buddhism.model.Message;
import com.buddhism.model.Post;
import com.buddhism.service.msgService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GodBlessedMay
 */
public class ArticleList extends SinglePostAction {
    
    private String title;
    private String nav;
    
    private List<ActionPair> actionPair;
    
    private List<Post> informs = new ArrayList<Post>();
    private List<Post> lastestMessage = new ArrayList<Post>();
    private List<Post> supports = new ArrayList<Post>();
    
    private List<Message> messages = new ArrayList<Message>();
    
    private msgService mService;

    public List<Post> getInforms() {
        return informs;
    }

    public void setInforms(List<Post> informs) {
        this.informs = informs;
    }

    public List<Post> getLastestMessage() {
        return lastestMessage;
    }

    public void setLastestMessage(List<Post> lastestMessage) {
        this.lastestMessage = lastestMessage;
    }

    public List<Post> getSupports() {
        return supports;
    }

    public void setSupports(List<Post> supports) {
        this.supports = supports;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public msgService getmService() {
        return mService;
    }

    public void setmService(msgService mService) {
        this.mService = mService;
    }            
    
    public ArticleList() 
    {
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    public void putToSession()
    {
        session.put("posts", posts);
        session.put("informs", informs);
        session.put("messages", lastestMessage);
        session.put("supports", supports);
        session.put("sms", messages);
    }
    
    public void pairToNav()
    {
        nav = "";
        
        for (int i = 0; i != actionPair.size(); i++)
        {
            ActionPair temp = actionPair.get(i);
            nav += "<a href=" + temp.getActionUrl() + " style=\"color:white\">" + temp.getActionName() + "</a> >> ";
        }
        
        nav += title + " >> 列表";
    }
    
    @Override
    public String execute()
    {
        title = Constants.columns[type];
        
        actionPair = ActionPair.getUrls(type);
        pairToNav();
        
        super.execute(); 
        
        lastestMessage = service.getPost((short)Constants.lastestNews, 0, 5);
        informs = service.getPost((short)Constants.informs, 0, 5);
        supports = service.getPost((short)Constants.supports, 0, 5);
        messages = mService.getMsg();
        
        putToSession();
        
        return "SUCCESS";
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the actionPair
     */
    public List<ActionPair> getActionPair() {
        return actionPair;
    }

    /**
     * @param actionPair the actionPair to set
     */
    public void setActionPair(List<ActionPair> actionPair) {
        this.actionPair = actionPair;
    }

    /**
     * @return the nav
     */
    public String getNav() {
        return nav;
    }

    /**
     * @param nav the nav to set
     */
    public void setNav(String nav) {
        this.nav = nav;
    }
    
    
}
