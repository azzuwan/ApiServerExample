/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazzify.simpleapiserverexample.models;

import java.util.Date;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.ObjectId;

/**
 *
 * @author Azzuwan Aziz <azzuwan@gmail.com>
 */
public class Article {
    @MongoId
    private ObjectId id;
    private String url;
    private String title;
    private String author;
    private String body;
    private Date published;
    private String agency;

    public Article() {
    }

    public Article(ObjectId id, String url, String title, String author, String body, Date published, String agency) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.author = author;
        this.body = body;
        this.published = published;
        this.agency = agency;
    }
    
    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
