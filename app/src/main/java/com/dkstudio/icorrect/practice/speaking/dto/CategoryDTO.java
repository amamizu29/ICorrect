package com.dkstudio.icorrect.practice.speaking.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by khiemnt on 06/07/2016.
 */
public class CategoryDTO
{
    @SerializedName("numOfQuestion")
    private int numOfQuestion;
    @SerializedName("id")
    private int id;
    @SerializedName("introduce")
    private String introduce;
    @SerializedName("videoUrl")
    private String  videoUrl;
    @SerializedName("type")
    private int type;
    @SerializedName("status")
    private int status;

    @SerializedName("listQuestion")
    private List<QuestionDTO> questionDTOList;

    public int getNumOfQuestion()
    {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion)
    {
        this.numOfQuestion = numOfQuestion;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getIntroduce()
    {
        return introduce;
    }

    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public String getVideoUrl()
    {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl)
    {
        this.videoUrl = videoUrl;
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

    public List<QuestionDTO> getQuestionDTOList()
    {
        return questionDTOList;
    }

    public void setQuestionDTOList(List<QuestionDTO> questionDTOList)
    {
        this.questionDTOList = questionDTOList;
    }
}
