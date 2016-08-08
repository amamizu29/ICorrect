package com.dkstudio.icorrect.practice.speaking.service;

import com.dkstudio.icorrect.practice.speaking.dto.InteractQuestionDTO;
import com.dkstudio.icorrect.practice.speaking.dto.ResponseDTO;
import com.dkstudio.icorrect.practice.speaking.dto.UserDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by khiemnt on 06/07/2016.
 */
public interface SpeakingService
{
    @GET("/api/icorrect/interactquestion.php")
    Call<InteractQuestionDTO> getData();

    @POST("/api/icorrect/login.php")
    Call<ResponseDTO<UserDTO>> getLogin();
}
