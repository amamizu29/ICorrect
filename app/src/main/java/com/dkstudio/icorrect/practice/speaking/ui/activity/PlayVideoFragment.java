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
import com.dkstudio.icorrect.practice.speaking.dto.CategoryDTO;
import com.dkstudio.icorrect.practice.speaking.dto.InteractQuestionDTO;
import com.dkstudio.icorrect.practice.speaking.dto.QuestionDTO;
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
    VideoView videoView;

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
        videoView = (VideoView) findViewById(R.id.myVideo);
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

        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this);
        customProgressDialog.show("");

        try
        {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(this);
            mediacontroller.setAnchorView(videoView);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(videoUrls.get(currentVideo));
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(video);
            videoView.requestFocus();

        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp)
            {
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
            }
        });
    }

    private void playVideo(String urlVideo)
    {
        Log.i("url--------", urlVideo);
        Uri vidUri = Uri.parse(urlVideo);
        videoView.setVideoURI(vidUri);
        videoView.start();
    }

}
