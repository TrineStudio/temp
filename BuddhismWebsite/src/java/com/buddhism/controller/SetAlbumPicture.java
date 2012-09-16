/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.service.AVService;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author 0
 */
public class SetAlbumPicture implements SessionAware{
    
    private Map session;
    
    private int packetID;
    private int mediaID;
    
    private AVService service;

    public int getPacketID() {
        return packetID;
    }

    public void setPacketID(int packetID) {
        this.packetID = packetID;
    }

    public int getMediaID() {
        return mediaID;
    }

    public void setMediaID(int mediaID) {
        this.mediaID = mediaID;
    }

    public AVService getService() {
        return service;
    }

    public void setService(AVService service) {
        this.service = service;
    }

    @Override
    public void setSession(Map session){
        this.session = session;
    }
    
    public String execute()
    {
        // 加一个 SetFirst 函数来设置 Packet 的封面相册
        // 函数接受2个参数 一个是 packetID 还有一个是 mediaID
        
        return "SUCCESS";
    
    }
    
    
    
}
