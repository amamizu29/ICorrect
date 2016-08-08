package com.dkstudio.icorrect.practice.speaking.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Common response DTO
 */
public class ResponseDTO<DTO> implements Serializable {
    @SerializedName("resultCode")
    private int resultCode ;
    @SerializedName("errorCode")
    private int errorCode ;
    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private DTO data;

    public ResponseDTO() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(int resultCode)
    {
        this.resultCode = resultCode;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public DTO getData()
    {
        return data;
    }

    public void setData(DTO data)
    {
        this.data = data;
    }
}

