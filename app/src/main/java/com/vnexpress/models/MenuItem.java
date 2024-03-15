package com.vnexpress.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MenuItem {
    private String title;
    private int icon;
    private boolean isBold=false;
    private boolean hideTitle=false;
    private boolean hideIcon=false;
    private boolean titleMargin=false;
    private boolean titleGray=false;
    private boolean hideIconRight=false;
    @Builder
    public MenuItem(String title, int icon, boolean isBold, boolean hideTitle, boolean hideIcon,boolean titleMargin,boolean titleGray,boolean hideIconRight) {
        this.title = title;
        this.icon = icon;
        this.isBold = isBold;
        this.hideTitle = hideTitle;
        this.hideIcon = hideIcon;
        this.titleMargin = titleMargin;
        this.titleGray = titleGray;
        this.hideIconRight = hideIconRight;
    }
}
