/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.model.Post;
import org.apache.struts2.ServletActionContext;
import org.htmlparser.beans.StringBean;

/**
 *
 * @author GodBlessedMay
 */
public class MainPageArticle extends SinglePostAction {
    
    private boolean isEnd = false;
    
    public MainPageArticle() {
        type = 0;
        max = 3;
        currentIndex = -1;
    }
    
    @Override
    public String execute()
    {
        currentIndex = -1;
        isEnd = false;
        
        maxIndex = service.getPostNumber(type);
        maxPage = maxIndex / max;
        
        return nextPage();
    }
    
    @Override
    public void getPost()
    {
        posts.clear();
        
        // List<Post> temp = new ArrayList<Post>();
        
        if (isEnd)
            return;
        
        if (max * currentIndex + max > maxIndex)
        {
            posts = service.getPost((short)type, currentIndex * max, maxIndex);
            isEnd = true;
        }         
        else
            posts = service.getPost((short)type, currentIndex * max, max);

        for (int i = 0; i != posts.size(); i++)
        {
            posts.get(i).setType();
        }
        
        for (int i = 0; i != posts.size(); i++)
        {
            String content = posts.get(i).getPostContent();
            
            if (content.contains("<img"))
            {
                posts.get(i).setPostTitle(posts.get(i).getPostTitle() + "(图)");
            }
            
            posts.get(i).setPostContent(splitAndFilterString(content, content.length()));
        }
    }
    
    public static String splitAndFilterString(String input, int length) {  
        if (input == null || input.trim().equals("")) {  
            return "";  
        }  
        // 去掉所有html元素,  
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(  
                "<[^>]*>", "");  
        str = str.replaceAll("[(/>)<]", "");  
        int len = str.length();  
        if (len <= length) {  
            return str;  
        } else {  
            str = str.substring(0, length);  
            str += "......";  
        }  
        return str;  
    }  
    
}
