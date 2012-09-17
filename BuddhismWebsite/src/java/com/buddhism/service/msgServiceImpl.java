/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.service;

import com.buddhism.dao.msgDaoImpl;
import com.buddhism.model.Message;

import java.util.Date;

/**
 *
 * @author nankonami
 */
public class msgServiceImpl implements msgService
{
    private msgDaoImpl msgDao;
    
    @Override
    public Message getMsg(int id) 
    {
        return msgDao.getMsg(id);
    }

    @Override
    public int getMsgNumber() 
    {
        return msgDao.getMsgNumber();
    }

    @Override
    public void addMsg(Message msg) 
    {
        msgDao.addMsg(msg);
    }

    @Override
    public void addMsg(String msgContent, String msgAuthor, String msgLink) 
    {
        Message msg = new Message();
        
        msg.setId(1);
        msg.setMessageAuthor(msgAuthor);
        msg.setMessageContent(msgContent);
        msg.setMessageDate(new Date(System.currentTimeMillis()));
        msg.setMessageLink(msgLink);
        
        msgDao.addMsg(msg);
    }

    @Override
    public void updateMsg(Message msg) 
    {
        msgDao.updateMsg(msg);
    }

    @Override
    public void deleteMsg(int id) 
    {
        msgDao.deleteMsg(id);
    }

    /**
     * @return the msgDao
     */
    public msgDaoImpl getMsgDao() {
        return msgDao;
    }

    /**
     * @param msgDao the msgDao to set
     */
    public void setMsgDao(msgDaoImpl msgDao) {
        this.msgDao = msgDao;
    }
    
}
