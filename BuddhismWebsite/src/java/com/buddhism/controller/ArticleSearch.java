/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.model.Post;
import com.buddhism.service.postService;
import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Administrator
 */
public class ArticleSearch {
    
    private postService service;
    
    private String key;
    
    private List<Post> posts = new ArrayList<Post>();
    
    private List<Post> currentPosts;
    
    private final int max = 20;
    
    private int currentIndex;
    private int maxIndex;
    
    protected int maxPage = 0;
    
    
    public String execute()
    {
    
        TransactSQLInjection(key);
        
        posts = service.getPostNameLike(key);
        
        maxIndex = posts.size();
        currentIndex = 0;
        
        maxPage = maxIndex / max;
        if (max > maxIndex)
            currentPosts = posts.subList(0, maxIndex);
        else
            currentPosts = posts.subList(0, max);
        
        putIntoRequest();
        
        return "SUCCESS";
    }
    
    public void putIntoRequest()
    {
        ActionContext ctx = ActionContext.getContext();       
      
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);       
        
        request.setAttribute("currentIndex", currentIndex);
        request.setAttribute("maxIndex", maxPage);
    }
    
    public static String TransactSQLInjection(String sql) {  
        return sql.replaceAll(".*([';]+|(--)+).*", " ");  
    } 
    
    
    public String nextPage()
    {
        currentIndex = currentIndex + 1 > maxPage ? maxPage : currentIndex + 1;
        
        if ((currentIndex + 1) * max > maxIndex)
            currentPosts = posts.subList(currentIndex * max, maxIndex);
        else            
            currentPosts = posts.subList(currentIndex * max, (currentIndex + 1) * max);
        
        putIntoRequest();
        
        return "SUCCESS";
    }
    
    public String jump()
    {
        currentPosts = posts.subList(currentIndex * max, (currentIndex + 1) * max);
        return "SUCCESS";
    }
    
    public String previousPage()
    {
        currentIndex = currentIndex -1 < 0 ? 0 : currentIndex - 1;
        
        if ((currentIndex + 1) * max > maxIndex)
            currentPosts = posts.subList(currentIndex * max, maxIndex);
        else            
            currentPosts = posts.subList(currentIndex * max, (currentIndex + 1) * max);
        
        putIntoRequest();
        
        return "SUCCESS";
    }

    public List<Post> getCurrentPosts() {
        return currentPosts;
    }

    public void setCurrentPosts(List<Post> currentPosts) {
        this.currentPosts = currentPosts;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }        

    public postService getService() {
        return service;
    }

    public void setService(postService service) {
        this.service = service;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
        
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    
    
}
