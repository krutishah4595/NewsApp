package com.example.homework1.newsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static ArrayList<NewsItem> parseNews(String Json) {

        ArrayList<NewsItem> list = new ArrayList<>();

        try {
            JSONObject js1 = new JSONObject(Json);

            if (js1.getString("status").trim().equalsIgnoreCase("ok")) {

                JSONArray array = js1.getJSONArray("articles");

                for (int i = 0; i < array.length(); i++) {

                    JSONObject articles = array.getJSONObject(i);

                    NewsItem newsItem = new NewsItem(
                            articles.getString("title"),
                            articles.getString("description"),
                            articles.getString("publishedAt"),
                            articles.getString("url"));


                    list.add(newsItem);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}


