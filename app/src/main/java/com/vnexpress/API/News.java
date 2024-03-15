package com.vnexpress.API;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class News implements Serializable {
    private String title="";
    private String description="";
    private String timeCount="";
    private String image="";
    private String link="";
@Builder
    public News(String title, String description, String timeCount, String image,String link) {
    this.link=link;
        this.title = title;
        this.description = description;
        this.timeCount = timeCount;
        this.image = image;
    }
}
