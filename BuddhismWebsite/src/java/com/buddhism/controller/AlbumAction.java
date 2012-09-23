/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buddhism.controller;

import com.buddhism.model.ActionPair;
import com.buddhism.model.Constants;
import com.buddhism.model.Message;
import com.buddhism.model.Packet;
import com.buddhism.model.Post;
import com.buddhism.service.AVService;
import com.buddhism.service.msgService;
import com.buddhism.service.postService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.htmlparser.beans.StringBean;

/**
 *
 * @author GodBlessedMay
 */
public class AlbumAction implements SessionAware{
    
    private Map session;
    
    private String title;
    private String nav;
    
    private List<ActionPair> actionPair;
    
    private AVService service;
    private List<Packet> albums = new ArrayList<Packet>();
    
    private int type;
    
    private List<Post> informs = new ArrayList<Post>();
    private List<Post> lastestMessage = new ArrayList<Post>();
    private List<Post> supports = new ArrayList<Post>();
    
    private List<Message> messages = new ArrayList<Message>();
    
    private msgService mService;
    
    private postService pService;
    
    @Override
    public void setSession(Map session){
        this.session = session;
    }

    public List<Post> getInforms() {
        return informs;
    }

    public void setInforms(List<Post> informs) {
        this.informs = informs;
    }

    public List<Post> getLastestMessage() {
        return lastestMessage;
    }

    public void setLastestMessage(List<Post> lastestMessage) {
        this.lastestMessage = lastestMessage;
    }

    public List<Post> getSupports() {
        return supports;
    }

    public void setSupports(List<Post> supports) {
        this.supports = supports;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public msgService getmService() {
        return mService;
    }

    public void setmService(msgService mService) {
        this.mService = mService;
    }

    public postService getpService() {
        return pService;
    }

    public void setpService(postService pService) {
        this.pService = pService;
    }
    
    public void putToSession()
    {
        session.put("informs", informs);
        session.put("messages", lastestMessage);
        session.put("supports", supports);
        session.put("sms", messages);
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
    
    public AlbumAction() {
    }
    
    public void pairToNav()
    {
        nav = "";
        
        for (int i = 0; i != actionPair.size(); i++)
        {
            ActionPair temp = actionPair.get(i);
            nav += "<a href=" + temp.getActionUrl() + " style=\"color:white\">" + temp.getActionName() + "</a> >> ";
        }
        
        nav += title + " >> 列表";
    }
    
    public String execute(){
        
        title = Constants.columns[type];
        
        actionPair = ActionPair.getUrls(type);
        pairToNav();
        if (type == 5)
            albums = service.getPackets(0);
        else if (type == 8)
            albums = service.getPackets(1);
        
        lastestMessage = pService.getPost((short)Constants.lastestNews, 0, 5);
        informs = pService.getPost((short)Constants.informs, 0, 5);
        supports = pService.getPost((short)Constants.supports, 0, 5);
        messages = mService.getMsg();
        
        putToSession();
        
        return "SUCCESS";
    }

    /**
     * @return the service
     */
    public AVService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(AVService service) {
        this.service = service;
    }

    /**
     * @return the albums
     */
    public List<Packet> getAlbums() {
        return albums;
    }

    /**
     * @param albums the albums to set
     */
    public void setAlbums(List<Packet> albums) {
        this.albums = albums;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * @return the actionPair
     */
    public List<ActionPair> getActionPair() {
        return actionPair;
    }

    /**
     * @param actionPair the actionPair to set
     */
    public void setActionPair(List<ActionPair> actionPair) {
        this.actionPair = actionPair;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
}
