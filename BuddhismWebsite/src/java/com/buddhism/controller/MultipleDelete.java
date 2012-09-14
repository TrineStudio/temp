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
    
    private postService pService;
    private AVService aService;
    
    private Map session;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public postService getpService() {
        return pService;
    }

    public void setpService(postService pService) {
        this.pService = pService;
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
        
        deleteData(checkID);

        if (type == 1)
            return "SUCCESS";
        else if (type == 2)
            return "CANCEL";
        else if (type == 3)
            return "ERROR";
        else if (type == 4)
            return "TRASH";
        
        return "NONE";                    
    }
    
    private void deleteData(String[] checkID)
    {
        for (int i = 0; i != checkID.length; i++)
        {
            int id = Integer.parseInt(checkID[i]);
            if (type == 1)
                pService.removePost(id);
            else if (type == 2 || type == 3)
                aService.deleteMedia(id);
            else if (type == 4)
                pService.deletePost(id);
        }
    }      
}
