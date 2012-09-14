/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.service.postService;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Administrator
 */
public class RestoreArticle implements SessionAware{
    
    private int id;
    
    private Map session;
    
    private postService service;

    public postService getService() {
        return service;
    }

    public void setService(postService service) {
        this.service = service;
    }
     
    @Override
    public void setSession(Map session) {   
       this.session = session;   
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String execute()
    {
        service.restorePost(id);
               
        return "SUCCESS";
    }
    
    
}
