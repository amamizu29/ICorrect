package com.dkstudio.icorrect.practice.speaking.ui.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import butterknife.ButterKnife;
import com.dkstudio.icorrect.R;
import com.dkstudio.icorrect.practice.speaking.dto.*;
import com.dkstudio.icorrect.practice.speaking.service.ServiceBuilder;
import com.dkstudio.icorrect.practice.speaking.ui.custom.CustomProgressDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PlayVideoFragment extends FragmentActivity
{
    String url = "https://api.github.com/users/mobilesiri";

    public TextView tvResultCode;
    public TextView tvCategory;
    VideoView myVideo1;
    MediaController mediaController1;
    MediaController mediaController2;
     CustomProgressDialog customProgressDialog;

    public int currentVideo = 0;
    public List<String> videoUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video);
        ButterKnife.bind(this);

        tvResultCode = (TextView) findViewById(R.id.txt_comp);
        tvCategory = (TextView) findViewById(R.id.txt_blog);
        myVideo1 = (VideoView) findViewById(R.id.myVideo1);
        ServiceBuilder.getService().getData().enqueue(new Callback<InteractQuestionDTO>()
        {
            @Override
            public void onResponse(Call<InteractQuestionDTO> call, Response<InteractQuestionDTO> response)
            {
                InteractQuestionDTO interactQuestionDTO = response.body();
                showVideo(interactQuestionDTO);
            }

            @Override
            public void onFailure(Call<InteractQuestionDTO> call, Throwable t)
            {
            }
        });

        ServiceBuilder.getService().getLogin().enqueue(new Callback<ResponseDTO<UserDTO>>()
        {
            @Override
            public void onResponse(Call<ResponseDTO<UserDTO>> call, Response<ResponseDTO<UserDTO>> response)
            {
                ResponseDTO<UserDTO> result=response.body();
                UserDTO userDTO=result.getData();
            }

            @Override
            public void onFailure(Call<ResponseDTO<UserDTO>> call, Throwable t)
            {

            }
        });
    }

    public void showVideo(InteractQuestionDTO interactQuestionDTO)
    {
        tvResultCode.setText(interactQuestionDTO.getResultCode() + "");
        tvCategory.setText(interactQuestionDTO.getCategoryId());

//        String categoryVideoUrl = "http://prosoftforlife.com/icorect/ct05.mp4";
        for (CategoryDTO categoryDTO : interactQuestionDTO.getCategoryDTOList())
        {
            videoUrls.add(categoryDTO.getVideoUrl());
            for (QuestionDTO questionDTO : categoryDTO.getQuestionDTOList())
            {
                videoUrls.add(questionDTO.getVideoDefaultUrl());
            }
        }

        try
        {
            // Start the MediaController
            mediaController1 = new MediaController(this);
            mediaController2 = new MediaController(this);

            mediaController1.setAnchorView(myVideo1);
            // Get the URL from String VideoURL
            prepareVideo(videoUrls.get(currentVideo),myVideo1,mediaController1);

        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        startVideo(myVideo1);
    }

    private void prepareVideo(String urlVideo,VideoView myVideo,MediaController mediaController)
    {
        customProgressDialog = new CustomProgressDialog(this);
        customProgressDialog.show("");
        Uri video = Uri.parse(urlVideo);
        myVideo.setMediaController(mediaController);
        myVideo.setVideoURI(video);
        myVideo.requestFocus();
    }

    private void startVideo(final VideoView videoView){
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp)
            {
//                mp.reset();
                customProgressDialog.dismiss("");
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                currentVideo++;
                Toast.makeText(PlayVideoFragment.this, "done video -next video: " + currentVideo, Toast.LENGTH_SHORT).show();
                if (currentVideo > videoUrls.size() - 6)
                {
                    videoView.stopPlayback();
                    return;
                }
                playVideo(videoUrls.get(currentVideo));
//                mp.release();
            }
        });
    }

    private void playVideo(String urlVideo)
    {
        Log.i("url--------", urlVideo);
        Uri vidUri = Uri.parse(urlVideo);
        myVideo1.setVideoURI(vidUri);
        myVideo1.start();
    }

}
