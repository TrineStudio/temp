/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.service.AVService;
import com.buddhism.service.postService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Administrator
 */
public class MultipleDelete implements SessionAware{        
    
    private int type;
    
    private AVService aService;
    
    private postService pService;

    public postService getpService() {
        return pService;
    }

    public void setpService(postService pService) {
        this.pService = pService;
    }

    
    private Map session;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AVService getaService() {
        return aService;
    }

    public void setaService(AVService aService) {
        this.aService = aService;
    }
           
    @Override
    public void setSession(Map session) {   
       this.session = session;   
    }
    
    public String execute()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        String[] checkID = request.getParameterValues("checkall");
        
        if (checkID != null)
            deleteData(checkID);

        if (type == 1)
            return "PHOTO";
        else if (type == 2)
            return "VIDEO";
        else if (type ==3)
            return "ARTICLE";
        else if (type == 4)
            return "TRASH";
        return "NONE";                    
    }
    
    private void deleteData(String[] checkID)
    {
        for (int i = 0; i != checkID.length; i++)
        {
            int id = Integer.parseInt(checkID[i]);
            if (type == 3 || type == 4)
                pService.removePost(id);
            else
                aService.deleteMedia(id);
        }
    }      
}
