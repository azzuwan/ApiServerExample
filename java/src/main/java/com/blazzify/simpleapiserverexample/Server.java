/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazzify.simpleapiserverexample;

import com.blazzify.simpleapiserverexample.models.Article;
import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import static spark.Spark.get;

/**
 *
 * @author Azzuwan Aziz <azzuwan@gmail.com>
 */
public class Server {

    public static void main(String[] args) {
        String host = "mongodb://azzuwan:Reddoor74@aws-ap-southeast-1-portal.2.dblayer.com:15501,aws-ap-southeast-1-portal.0.dblayer.com:15501/news";
        DB db = new MongoClient(host).getDB("dbname");

        Jongo jongo = new Jongo(db);
        MongoCollection articles = jongo.getCollection("articles");
        get("/", (req, res) -> "Hello World");
        get("/:name", (req, res) -> {
            MongoCursor<Article> all = articles.find("{}").as(Article.class);
            return json(all.iterator());
        });
    }

    public static String json(Iterator<Article> iterator) {
        List<Article> articles = new ArrayList<>();
        iterator.forEachRemaining(articles::add);
        return new Gson().toJson(articles);
    }

}
