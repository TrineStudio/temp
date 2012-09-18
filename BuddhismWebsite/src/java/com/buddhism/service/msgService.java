/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.service;

import com.buddhism.model.Message;
import java.util.List;

/**
 *
 * @author nankonami
 */
public interface msgService 
{
    public Message getMsg(int id); //根据id获取某条message
    
    public List<Message> getMsg(); //获取所有的message
    
    public int getMsgNumber(); //获取所有的message的数量
    
    //add a piece of message
    public void addMsg(Message msg);
    public void addMsg(String msgContent, String msgAuthor, String msgLink);
    
    public void updateMsg(Message msg); //update a piece of message
    
    public void deleteMsg(int id); //delete message according to the id
}
