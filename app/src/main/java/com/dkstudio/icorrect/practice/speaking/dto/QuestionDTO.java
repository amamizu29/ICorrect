package com.dkstudio.icorrect.practice.speaking.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khiemnt on 06/07/2016.
 */
public class QuestionDTO
{
    @SerializedName("id")
    private int id;
    @SerializedName("question")
    private String question;
    @SerializedName("videoDefaultUrl")
    private String videoDefaultUrl;
    @SerializedName("videoSlowUrl")
    private String videoSlowUrl;
    @SerializedName("videoDetailUrl")
    private String videoDetailUrl;
    @SerializedName("categoryId")
    private int categoryId;
    @SerializedName("type")
    private int type;
    @SerializedName("status")
    private int status;

    //============GETTER && SETTER===============
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getVideoDefaultUrl()
    {
        return videoDefaultUrl;
    }

    public void setVideoDefaultUrl(String videoDefaultUrl)
    {
        this.videoDefaultUrl = videoDefaultUrl;
    }
//
    public String getVideoSlowUrl()
    {
        return videoSlowUrl;
    }

    public void setVideoSlowUrl(String videoSlowUrl)
    {
        this.videoSlowUrl = videoSlowUrl;
    }

    public String getVideoDetailUrl()
    {
        return videoDetailUrl;
    }

    public void setVideoDetailUrl(String videoDetailUrl)
    {
        this.videoDetailUrl = videoDetailUrl;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
