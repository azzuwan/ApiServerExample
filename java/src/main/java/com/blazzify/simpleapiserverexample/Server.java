/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazzify.simpleapiserverexample;

import com.blazzify.simpleapiserverexample.models.Article;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import static spark.Spark.get;

/**
 *
 * @author Azzuwan Aziz <azzuwan@gmail.com>
 */
public class Server {

    public static void main(String[] args) {
        //String host = "mongodb://azzuwan:Reddoor74@aws-ap-southeast-1-portal.2.dblayer.com:15501,aws-ap-southeast-1-portal.0.dblayer.com:15501/admin";
        String host = "mongodb://azzuwan:Reddoor74@aws-ap-southeast-1-portal.2.dblayer.com:15501/admin";
        MongoClientURI uri = new MongoClientURI(host);
        MongoDatabase db = new MongoClient(uri).getDatabase("news");
        MongoCollection<Document> articles = db.getCollection("articles");
        get("/", (req, res) -> "Hello World");
        get("/:name", (req, res) -> {            
            MongoCursor<Document> all = articles.find().iterator();
            Document a = all.next();
            return a.get("title");
        });
    }

    public static String json(Iterator<Article> iterator) {
        List<Article> articles = new ArrayList<>();
        iterator.forEachRemaining(articles::add);
        System.out.println("XXXXX: " + articles.get(0).getTitle());
        String out = new Gson().toJson(articles);
        System.out.println("BBBBBBBBBBB: " + out);
        return new Gson().toJson(articles);
    }

}
