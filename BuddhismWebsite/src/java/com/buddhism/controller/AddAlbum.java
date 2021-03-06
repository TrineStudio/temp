/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.model.Administrator;
import com.buddhism.model.Packet;
import com.buddhism.service.AVServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author GodBlessedMay
 */
public class AddAlbum extends ActionSupport implements SessionAware{
    private String title;
    private String pageTitle;
    private String description;
    
    private int type;
    
    protected Map session;
    
    private AVServiceImpl service;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public void setSession(Map session) {   
  
       this.session = session;   
  
  
    }      
    
    public AddAlbum() {
    }
    
    public String add()
    {
        // title 相册名
        Administrator ad = (Administrator)session.get("User");
        
        service.addPacket(ad, title, type, pageTitle, description);//最后一个参数应该是packet的cover，第一个参数是packet的administrator
        
        if (type == 0)        
            return "SUCCESS";
        else 
            return "CANCEL";
    }
    
    public String execute() throws Exception {
        
        if (type == 0)
            pageTitle = "相册集";
        else if (type == 1)
            pageTitle = "视频集";
        
        return "SUCCESS";
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the pageTitle
     */
    public String getPageTitle() {
        return pageTitle;
    }

    /**
     * @param pageTitle the pageTitle to set
     */
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the service
     */
    public AVServiceImpl getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(AVServiceImpl service) {
        this.service = service;
    }
}
