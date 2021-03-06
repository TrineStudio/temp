/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.dao;

import com.buddhism.model.Administrator;
import com.buddhism.model.Media;
import com.buddhism.model.Packet;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author nankonami
 */
public class AVDaoImpl extends HibernateDaoSupport implements AVDao
{

    private static String PACKETQUERYSTRING = "from Packet as p where "; 
    private static String MEDIAQUERYSTRING = "from Media as m where ";
    
    @Override
    public Packet getP(int id) 
    {
       Session s = this.getSession();
       s.beginTransaction();
       
       String hql = "p.id = :id";
       Query query = s.createQuery(PACKETQUERYSTRING + hql);
       query.setParameter("id", (short)id);
       
       s.getTransaction().commit();
       //s.close();
       
       return (Packet)query.list().get(0);
    }

    @Override
    public Packet getP(String name) 
    {
       Session s = this.getSession();
       s.beginTransaction();
       
       String hql = "p.packetTitle = :title";
       Query query = s.createQuery(PACKETQUERYSTRING + hql);
       query.setParameter("title", name);
       
       s.getTransaction().commit();
       //s.close();
       
       return (Packet)query.list().get(0);
    }

    @Override
    public int getPN(int type) 
    {
        List list = getHibernateTemplate().find("select count(*) from Packet as p where p.packetType = ?", (short)type);
        return ((Long)list.iterator().next()).intValue();
    }

    @Override
    public List<Packet> getPS(final Administrator ad, final int packetType) 
    {
       return getHibernateTemplate().executeFind(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException 
            {
                if(ad.getAdLevel() == 0)
                {
                    Query query = sn.createQuery(PACKETQUERYSTRING + "p.packetType = :type order by p.packetDate desc");
                    query.setParameter("type", (short)packetType);
                    return query.list();
                }else
                {
                    Query query = sn.createQuery(PACKETQUERYSTRING + "p.packetType = :type and p.administrator = :ad order by p.packetDate desc");
                    query.setParameter("type", (short)packetType);
                    query.setParameter("ad", ad);
                    return query.list();
                }              
            }
        });
    }

    @Override
    public void addP(Packet p) 
    {
        getHibernateTemplate().save(p);
    }

    @Override
    public void deleteP(int id) 
    {
       Packet packet = this.getP(id);
       Iterator<Media> it = packet.getMedias().iterator();
       
       while(it.hasNext())
       {
           Media media = it.next();
           File file = new File(media.getMediaUrl());
           if(file.isFile() && file.exists())
           {
               file.delete();
           }
       }
       
       
       Session s = this.getSession();
       s.beginTransaction();
       
       Query query = s.createQuery("delete from Packet as p where p.id = :id");
       query.setParameter("id", (short)id);
       query.executeUpdate();
       
       s.getTransaction().commit();
       s.close();
    }

    @Override
    public void updateP(int id, int type, String name, String first, String desc, Date date) 
    {
        Session s = this.getSession();
        s.beginTransaction();
        
        String hqlString = "update Packet as p set p.packetType = :type, p.packetTitle = :name"
                + ", p.packetFirst = :frist, p.packetDesc = :desc, p.packetDate = :date where p.id = :id";
        Query query = s.createQuery(hqlString);
        
        query.setParameter("type", (short)type);
        query.setParameter("name",name);
        query.setParameter("frist", first);
        query.setParameter("desc", desc);
        query.setParameter("date", date);
        query.setParameter("id", (short)id);
        
        query.executeUpdate();
        
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public Media getM(int id) 
    {
        String hql = MEDIAQUERYSTRING + "m.id = ?";
        List list = getHibernateTemplate().find(hql, id);
        return (Media)list.get(list.size() - 1);
    }

    @Override
    public Media getM(String title) 
    {
        String hql = MEDIAQUERYSTRING + "m.mediaDesc = ?";
        return (Media)getHibernateTemplate().find(hql, title).get(0);
    }

    @Override
    public int getMN(int type) 
    {
        List list = getHibernateTemplate().find("select count(*) from Media as m where m.mediaType = " + (short)type);
        return ((Long)list.iterator().next()).intValue();
    }

    @Override
    public List<Media> getMS(final Administrator ad, final int type, final int offset, final int length) 
    {
        return getHibernateTemplate().executeFind(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
               Query query = sn.createQuery("from Media as m where m.administrator = :ad and m.mediaType = :type order by m.mediaDate desc");
               query.setParameter("ad", ad);
               query.setParameter("type", (short)type);
               query.setFirstResult(offset);
               query.setMaxResults(length);
               return query.list();
            }
        });
    }

    @Override
    public void addM(Media m) 
    {
        getHibernateTemplate().save(m);
    }

    @Override
    public void deleteM(int id) 
    {
       Media media = this.getM(id);
       
       String path = media.getMediaUrl();
            
       int i = 1;
            
       for (; i != path.length(); i++)
       if (path.charAt(i) == '\\' || path.charAt(i) == '/')
          break;
            
       path = path.substring(i);
            
       path = ServletActionContext.getServletContext().getRealPath(path);
       File file = new File(path);
       
       file.delete();
       
       Session s = this.getSession();
       s.beginTransaction();
       
       Query query = s.createQuery("delete from Media as m where m.id = :id");
       query.setParameter("id", id);
       
       query.executeUpdate();;
       
       s.getTransaction().commit();
       s.close();
    }

    @Override
    public void updateM(int id, short type, String desc, Date date) 
    {
        Session s = this.getSession();
        s.beginTransaction();
        
        String hqlString = "update Media as m set m.mediaType = :type, m.mediaDesc = :desc"
                + ", m.mediaDate = :date where m.id = :id";
        Query query = s.createQuery(hqlString);
        
        query.setParameter("type", type);
        query.setParameter("desc", desc);
        query.setParameter("date", date);
        query.setParameter("id", id);
        
        query.executeUpdate();
        
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public int getPN(Administrator ad, int type) 
    {
        Session s = this.getSession();
        
        if(ad.getAdLevel() == 0)
        {
            return this.getPN(type);
        }else
        {
            Query query = s.createQuery(PACKETQUERYSTRING + "p.packetType = :type and p.administrator = :ad");
            query.setParameter("type", (short)type);
            query.setParameter("ad", ad);
            return query.list().size();
        }
    }

    @Override
    public List<Media> getMS(final int packetId, final int offset, final int length) 
    {
        return getHibernateTemplate().executeFind(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
               Query query = sn.createQuery(MEDIAQUERYSTRING + "m.packet = :packet order by m.mediaDate desc");
               query.setParameter("packet", getP(packetId));
               query.setFirstResult(offset);
               query.setMaxResults(length);
               return query.list();
            }
        });
    }

    @Override
    public List<Media> getMSN(final int type, final int offset, final int length) 
    {
        return getHibernateTemplate().executeFind(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
               Query query = sn.createQuery(MEDIAQUERYSTRING + "m.mediaType = :type order by m.mediaDate desc");
               query.setParameter("type", (short)type);
               query.setFirstResult(offset);
               query.setMaxResults(length);
               return query.list();
            }
        });
    }

    @Override
    public List<Packet> getPS(final int packetType) 
    {
       return getHibernateTemplate().executeFind(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException 
            {
               
                    Query query = sn.createQuery(PACKETQUERYSTRING + "p.packetType = :type order by p.packetDate desc");
                    query.setParameter("type", (short)packetType);
                    return query.list();
            }
        });
    }

    @Override
    public void addMediaClickTimes(int mediaId) 
    {
        Session s = this.getSession();
        s.beginTransaction();
        
        String hqlString = "update Media as m set m.mediaClickTimes = m.mediaClickTimes + 1 where m.id = :id";
        Query query = s.createQuery(hqlString);
        
        query.setParameter("id", mediaId);
        
        query.executeUpdate();
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public int getMediaClickTimes(int mediaId) 
    {
        Session s = this.getSession();
        s.beginTransaction();
        
        String hqlString = "select new Media(m.mediaClickTimes) from Media m where m.id = :id";
        Query query = s.createQuery(hqlString);
        
        query.setParameter("id", mediaId);
        
        List list = query.list();
        s.getTransaction().commit();
        //s.close();
        
        return ((Media)list.get(0)).getMediaClickTimes();
    }

    @Override
    public int getMediaBetweenAnd(String start, String end, int type) 
    {
        Session s = this.getSession();
        s.beginTransaction();
        
        Criteria criteria = s.createCriteria(Media.class);
        Date startDate = java.sql.Date.valueOf(start);
        Date endDate = java.sql.Date.valueOf(end);
        
        criteria.add(Expression.between("mediaDate", endDate, startDate));
        criteria.add(Expression.eq("mediaType", (short)type));
        List list = criteria.list();
        s.getTransaction().commit();
        //s.close();
        
        return list.size();
    }

    @Override
    public void updateP(int packetId, int mediaId) 
    {
        Session s = this.getSession();
        s.beginTransaction();
        
        Media media = this.getM(mediaId);
        String hqlString = "update Packet as p set p.packetFirst = :first where p.id = :id";
        
        Query query = s.createQuery(hqlString);
        query.setParameter("first", media.getMediaUrl());
        query.setParameter("id", (short)packetId);
        
        query.executeUpdate();
        
        s.getTransaction().commit();
        s.close();
    }
}
