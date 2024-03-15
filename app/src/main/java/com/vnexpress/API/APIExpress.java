package com.vnexpress.API;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

public class APIExpress {
    private static final String BASE_URL = "https://thanhnien.vn/";

    Document document;


    @SneakyThrows
    public APIExpress() {


    }

    public News getFullNews(News news) {
        Document document = connect(news.getLink());
        Element content = document.getElementsByClass("detail-content afcbc-body").first();
        Elements elements = content.getAllElements();
        String res = "";
        for (Element element : elements) {
                if(element.is("p")||element.is("figure")) {

                   res+= element.toString();
                }
        }

        news.setDescription(res);
        return news;
    }

    public List<News> getNews(String category) {
        List<News> news = new ArrayList<>();

        Document document = connect(BASE_URL + (category==""?"":category+".htm"));

        Elements articles = document.getElementsByClass("box-category-item");
        for (Element article : articles) {

            String image = article.getElementsByTag("img").attr("src");
            if(image==""){
                continue;
            }
            String title = article.getElementsByTag("a").attr("title");
            String time = article.getElementsByClass("box-time time-ago").attr("title");
            String link = "https://thanhnien.vn" + article.getElementsByTag("a").attr("href");
            news.add(News.builder().title(title).image(image).timeCount(time).link(link).build());
        }
        return news;
    }


    @SneakyThrows
    private Document connect(String url) {
        return Jsoup.connect(url).get();
    }
}


