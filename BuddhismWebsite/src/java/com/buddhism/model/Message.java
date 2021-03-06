package com.buddhism.model;
// Generated 2012-9-18 3:12:13 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Message generated by hbm2java
 */
public class Message  implements java.io.Serializable {


     private Integer id;
     private String messageContent;
     private String messageAuthor;
     private Date messageDate;
     private String messageLink;

    public Message() {
    }

	
    public Message(String messageContent, String messageAuthor, Date messageDate) {
        this.messageContent = messageContent;
        this.messageAuthor = messageAuthor;
        this.messageDate = messageDate;
    }
    public Message(String messageContent, String messageAuthor, Date messageDate, String messageLink) {
       this.messageContent = messageContent;
       this.messageAuthor = messageAuthor;
       this.messageDate = messageDate;
       this.messageLink = messageLink;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMessageContent() {
        return this.messageContent;
    }
    
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    public String getMessageAuthor() {
        return this.messageAuthor;
    }
    
    public void setMessageAuthor(String messageAuthor) {
        this.messageAuthor = messageAuthor;
    }
    public Date getMessageDate() {
        return this.messageDate;
    }
    
    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
    public String getMessageLink() {
        return this.messageLink;
    }
    
    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }




}


