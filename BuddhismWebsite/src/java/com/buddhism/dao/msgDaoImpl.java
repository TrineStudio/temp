/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.dao;

import com.buddhism.model.Message;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author nankonami
 */
public class msgDaoImpl extends HibernateDaoSupport implements msgDao
{

    @Override
    public Message getMsg(int id) 
    {
        return (Message)getHibernateTemplate().find("from Message as m where m.id = ?", id).get(0);
    }

    @Override
    public void addMsg(Message msg) 
    {
        getHibernateTemplate().save(msg);
    }

    @Override
    public void updateMsg(Message msg) 
    {
        Session s = this.getSession();
        s.beginTransaction();
        
        String hqlString = "update Message m set m.messageContent = :content, m.messageAuthor = :author,"
                + " m.messageDate = :date, m.messageLink = :link where m.id = :id";
        
        Query query = s.createQuery(hqlString);
        query.setParameter("content", msg.getMessageContent());
        query.setParameter("author", msg.getMessageAuthor());
        query.setParameter("date", msg.getMessageDate());
        query.setParameter("link", msg.getMessageLink());
        query.setParameter("id", msg.getId());
        
        query.executeUpdate();
        s.getTransaction().commit();
       
        s.close();
    }

    @Override
    public void deleteMsg(int id) 
    {
        Session s = this.getSession();
        s.beginTransaction();
        
        Query query = s.createQuery("delete from Message m where m.id = :id");
        query.setParameter("id", id);
        
        query.executeUpdate();
        
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public int getMsgNumber() 
    {
        List list = getHibernateTemplate().find("select count(*) from Message");
        return ((Long)list.iterator().next()).intValue();
    }

    @Override
    public List<Message> getMsg() 
    {
        return getHibernateTemplate().executeFind(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException 
            {
                    Query query = sn.createQuery("from Message as m order by m.messageDate desc");
                    return query.list();
            }
        });
    }
    
}
