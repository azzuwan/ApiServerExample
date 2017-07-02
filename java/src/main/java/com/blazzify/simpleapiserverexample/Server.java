/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazzify.simpleapiserverexample;

import com.blazzify.simpleapiserverexample.models.Article;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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
        String host = "mongodb://azzuwan:Reddoor74@aws-ap-southeast-1-portal.2.dblayer.com:15501/admin";
        MongoClientURI uri = new MongoClientURI(host);
        MongoDatabase db = new MongoClient(uri).getDatabase("news");
        MongoCollection<Document> articles = db.getCollection("articles");
        get("/", (req, res) -> "Hello World");
        get("/articles/:keyword", (req, res) -> {
            String keyword = req.params(":keyword");
            MongoCursor<Document> all = articles.find(Filters.text(keyword)).iterator();
            return json(all);
        });
    }

    public static String json(MongoCursor<Document> cursor) {
        JsonParser parser = new JsonParser();
        JsonArray array = new JsonArray();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            JsonElement obj = parser.parse(doc.toJson());
            array.add(obj);
        }
        return array.getAsString();
    }
}
