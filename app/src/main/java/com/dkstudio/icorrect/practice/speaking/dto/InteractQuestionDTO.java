package com.dkstudio.icorrect.practice.speaking.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by khiemnt on 06/07/2016.
 */
public class InteractQuestionDTO
{
    @SerializedName("resultCode")
    private int resultCode ;
    @SerializedName("categoryId")
    private String categoryId ;
    @SerializedName("category")
    private List<CategoryDTO> categoryDTOList;

    public int getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(int resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public List<CategoryDTO> getCategoryDTOList()
    {
        return categoryDTOList;
    }

    public void setCategoryDTOList(List<CategoryDTO> categoryDTOList)
    {
        this.categoryDTOList = categoryDTOList;
    }
}
