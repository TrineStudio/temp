/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.service.postService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author 0
 */
public class TrashMultiAction implements SessionAware{
    
    private postService pService;
    
    private int type;
    
    private int selectIndex;
    
    private Map session;
    
    @Override
    public void setSession(Map session) {   
       this.session = session;   
    }

    public postService getpService() {
        return pService;
    }

    public void setpService(postService pService) {
        this.pService = pService;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }
    
    

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
  public String execute()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        String[] checkID = request.getParameterValues("checkall");
        
        if (checkID == null)
            return "SUCCESS";
        
        if (selectIndex == 1)
            deleteData(checkID);
        else if (selectIndex == 2)
            restoreData(checkID);

        return "SUCCESS";               
    }
    
    private void deleteData(String[] checkID)
    {
        for (int i = 0; i != checkID.length; i++)
        {
            int id = Integer.parseInt(checkID[i]);
            pService.deletePost(id);
        }
    }      
    
    private void restoreData(String[] checkID)
    {
        for (int i = 0; i != checkID.length; i++)
        {
            int id = Integer.parseInt(checkID[i]);
            pService.restorePost(id);
        }
    }
    
}
