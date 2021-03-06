package com.buddhism.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.buddhism.model.ActionPair;
import com.buddhism.model.Constants;
import com.buddhism.model.Post;
import com.buddhism.service.postService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.htmlparser.beans.StringBean;

/**
 *
 * @author GodBlessedMay
 */
public class ArticlePage extends ActionSupport implements SessionAware{
    
    private Post post;
    
    private postService service;
    
    private int id;

    private int index;
    
    protected Map session;
    
    private int parentType;
    
    private String nav;
    
   
    
    @Override
    public void setSession(Map session) {   
  
       this.session = session;   
  
  
    }  
    
    public void pairToNav(List<ActionPair> actionPair)
    {
        setNav("");
        
        for (int i = 0; i != actionPair.size(); i++)
        {
            ActionPair temp = actionPair.get(i);
            setNav(getNav() + "<a href=" + temp.getActionUrl() + " style=\"color:white\">" + temp.getActionName() + "</a> >> ");
        }
        
        nav += "正文";
    }    
    
    public ArticlePage() {
    }
    
    public void getContent(Post post)
    {
        StringBean sb = new StringBean();
        
        if (post.getPostContent().contains("<img"))
            post.setPostTitle(post.getPostTitle() + "(图)");
        
        sb.setLinks(false);
        sb.setReplaceNonBreakingSpaces(true);
        sb.setCollapse(true);
        
        sb.setURL("articleContent.action?id=" + post.getId());
        
        post.setPostContent(sb.toString());
    }
    
    public String execute() throws Exception {
        
        post = service.getPost(id);
        service.addPostClickTimes(id);
        
        List<ActionPair> actionPair = new ArrayList<ActionPair>();
        
        if (parentType != -1)
        {
            actionPair = ActionPair.getUrls(parentType);
            actionPair.add(new ActionPair(Constants.columns[parentType], Constants.actionUrl + parentType));
        }
        else
            actionPair.add(new ActionPair("首页", Constants.mainPageUrl));
        
        
        
        pairToNav(actionPair);
        
        
        
        session.put("index", index);
        
        return "SUCCESS";
    }

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * @return the service
     */
    public postService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(postService service) {
        this.service = service;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the parentType
     */
    public int getParentType() {
        return parentType;
    }

    /**
     * @param parentType the parentType to set
     */
    public void setParentType(int parentType) {
        this.parentType = parentType;
    }

    /**
     * @return the nav
     */
    public String getNav() {
        return nav;
    }

    /**
     * @param nav the nav to set
     */
    public void setNav(String nav) {
        this.nav = nav;
    }
}
