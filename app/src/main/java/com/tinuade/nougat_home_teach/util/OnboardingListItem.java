package com.tinuade.nougat_home_teach.util;

public class OnboardingListItem {
    private String title, Description;
    private   int imageResourceId;

    public OnboardingListItem(String title, String description, int imageResourceId) {
        this.title = title;
        Description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}


