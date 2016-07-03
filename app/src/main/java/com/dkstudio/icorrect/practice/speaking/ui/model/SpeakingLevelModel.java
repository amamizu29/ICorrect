package com.dkstudio.icorrect.practice.speaking.ui.model;

/**
 * Created by Administrator on 03/07/2016.
 */
public class SpeakingLevelModel {
    private String title;
    private String description;

    public SpeakingLevelModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //========SETTER && GETTER===================
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
