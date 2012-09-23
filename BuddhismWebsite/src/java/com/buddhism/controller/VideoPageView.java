/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.model.ActionPair;
import com.buddhism.model.Constants;
import com.buddhism.service.AVService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class VideoPageView {
    
    private String src;
    
    private int id;
    
    private int packetID;
    
    private String packetTitle;
    
    private String nav;
    
    private AVService service;

    public AVService getService() {
        return service;
    }

    public void setService(AVService service) {
        this.service = service;
    }
    
    public String getPacketTitle() {
        return packetTitle;
    }

    public void setPacketTitle(String packetTitle) {
        this.packetTitle = packetTitle;
    }
    
    

    public int getPacketID() {
        return packetID;
    }

    public void setPacketID(int packetID) {
        this.packetID = packetID;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }
    
    

    public void pairToNav(List<ActionPair> actionPair)
    {
        setNav("");
        
        for (int i = 0; i != actionPair.size(); i++)
        {
            ActionPair temp = actionPair.get(i);
            setNav(getNav() + "<a href=" + temp.getActionUrl() + " style=\"color:white\">" + temp.getActionName() + "</a> >> ");
        }
        
        nav += "<a href=mediumAction?packetId" + packetID + ">" + packetTitle + "</a> >> ";
        
        nav += "视频";
    }    
    
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String execute()
    {
        List<ActionPair> actionPair = new ArrayList<ActionPair>();
        
        int parentType = Constants.videoes;
         
        packetTitle = service.getPacket(packetID).getPacketTitle();
        
        actionPair = ActionPair.getUrls(parentType);
        actionPair.add(new ActionPair(Constants.columns[parentType], Constants.albumUrl + parentType));
        
        pairToNav(actionPair);        
        
        return "SUCCESS";
    }
    
}
