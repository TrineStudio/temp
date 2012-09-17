/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.dao;

import com.buddhism.model.Message;

/**
 *
 * @author nankonami
 */
public interface msgDao 
{
    public Message getMsg(int id);
    
    public void addMsg(Message msg);
    
    public void updateMsg(Message msg);
    
    public void deleteMsg(int id);
    
    public int getMsgNumber();
}
