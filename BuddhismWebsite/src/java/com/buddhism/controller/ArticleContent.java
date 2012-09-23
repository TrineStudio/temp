/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.service.postService;

/**
 *
 * @author Administrator
 */
public class ArticleContent {
    
    private int id;
    
    private postService service;
    
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public postService getService() {
        return service;
    }

    public void setService(postService service) {
        this.service = service;
    }
    
    public String execute()
    {
        content = service.getPost(id).getPostContent();
        
        return "SUCCESS";
    }
    
}
