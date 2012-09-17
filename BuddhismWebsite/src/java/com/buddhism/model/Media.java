package com.buddhism.model;
// Generated 2012-9-18 3:12:13 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Media generated by hbm2java
 */
public class Media  implements java.io.Serializable {


     private Integer id;
     private Packet packet;
     private Administrator administrator;
     private String mediaUrl;
     private short mediaType;
     private String mediaDesc;
     private Date mediaDate;
     private int mediaClickTimes;

    public Media() {
    }

	
    public Media(Packet packet, Administrator administrator, String mediaUrl, short mediaType, Date mediaDate, int mediaClickTimes) {
        this.packet = packet;
        this.administrator = administrator;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.mediaDate = mediaDate;
        this.mediaClickTimes = mediaClickTimes;
    }
    public Media(Packet packet, Administrator administrator, String mediaUrl, short mediaType, String mediaDesc, Date mediaDate, int mediaClickTimes) {
       this.packet = packet;
       this.administrator = administrator;
       this.mediaUrl = mediaUrl;
       this.mediaType = mediaType;
       this.mediaDesc = mediaDesc;
       this.mediaDate = mediaDate;
       this.mediaClickTimes = mediaClickTimes;
    }
   
    public Media(int mediaClickTimes)
    {
        this.mediaClickTimes = mediaClickTimes;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(Packet packet) {
        this.packet = packet;
    }
    public Administrator getAdministrator() {
        return this.administrator;
    }
    
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
    public String getMediaUrl() {
        return this.mediaUrl;
    }
    
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
    public short getMediaType() {
        return this.mediaType;
    }
    
    public void setMediaType(short mediaType) {
        this.mediaType = mediaType;
    }
    public String getMediaDesc() {
        return this.mediaDesc;
    }
    
    public void setMediaDesc(String mediaDesc) {
        this.mediaDesc = mediaDesc;
    }
    public Date getMediaDate() {
        return this.mediaDate;
    }
    
    public void setMediaDate(Date mediaDate) {
        this.mediaDate = mediaDate;
    }
    public int getMediaClickTimes() {
        return this.mediaClickTimes;
    }
    
    public void setMediaClickTimes(int mediaClickTimes) {
        this.mediaClickTimes = mediaClickTimes;
    }




}


