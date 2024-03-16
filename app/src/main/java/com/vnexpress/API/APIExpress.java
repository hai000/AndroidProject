package com.vnexpress.API;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import com.vnexpress.models.ObserverNetworking;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIExpress extends ObserverNetworking {
    private static final String BASE_URL = "https://thanhnien.vn/";

    Document document;
    String linkMp3 = "";


    @SneakyThrows
    public APIExpress() {


    }

    public News getFullNews(News news) throws ExecutionException, InterruptedException {
        Document document = connect(news.getLink());
        Element content = document.getElementsByClass("detail-content afcbc-body").first();
        Elements elements = content.getAllElements();
        String res = "";
        if (news.getCategory().equalsIgnoreCase(Category.podcast)) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(news.getLink())
                    .build();


            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Document documentMP3 = Jsoup.parse(response.body().string());
                        Elements elements = documentMP3.getElementsByTag("script");
                        Element elementVideo = null;
                        int j =0 ;
                        for (int i = elements.size()-1; i > 0; i--) {
                            if (!elements.get(i).hasAttr("type")) {
                                if(j>2) {
                                    elementVideo=elements.get(i);
                                    break;
                                }else {
                                    j++;
                                }


                            }
                        }
                        String script = elementVideo.toString();
                        int start = script.indexOf("file: '");
                        int end = script.indexOf(".mp3'", start);
                       linkMp3  = script.substring(start+7 , end+4 );
                      News news = News.builder().linkMp3(linkMp3).build()   ;


                        notifyObserver(news);
                    } else {
                        System.out.println("Lỗi: " + response.code() + " " + response.message());
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }
            });

        }
        for (Element element : elements) {

            if (element.is("p") || element.is("figure")) {

                res += element.toString();
            }
        }

        news.setDescription(res);
        return news;
    }

    public List<News> getNews(String category) {
        List<News> news = new ArrayList<>();

        Document document = connect(BASE_URL + (category == "" ? "" : category + ".htm"));

        Elements articles = document.getElementsByClass("box-category-item");

        for (Element article : articles) {

            String image = article.getElementsByTag("img").attr("src");
            if (image == "") {
                continue;
            }
            String title = article.getElementsByTag("a").attr("title");
            String time = article.getElementsByClass("box-time time-ago").attr("title");
            String link = "https://thanhnien.vn" + article.getElementsByTag("a").attr("href");
            news.add(News.builder().title(title).image(image).timeCount(time).link(link).category(category).build());
        }
        return news;
    }


    @SneakyThrows
    private Document connect(String url) {
        return Jsoup.connect(url).get();
    }

}


