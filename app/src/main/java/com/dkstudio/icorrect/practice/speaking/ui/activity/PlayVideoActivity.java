package com.dkstudio.icorrect.practice.speaking.ui.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import butterknife.ButterKnife;
import com.dkstudio.icorrect.R;
import com.dkstudio.icorrect.practice.speaking.dto.CategoryDTO;
import com.dkstudio.icorrect.practice.speaking.dto.InteractQuestionDTO;
import com.dkstudio.icorrect.practice.speaking.dto.QuestionDTO;
import com.dkstudio.icorrect.practice.speaking.event.QuestionEvent;
import com.dkstudio.icorrect.practice.speaking.service.ServiceBuilder;
import com.dkstudio.icorrect.practice.speaking.ui.custom.CustomProgressDialog;
import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayVideoActivity extends Activity
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
        EventBus.getDefault().register(this);
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
                EventBus.getDefault().post(new QuestionEvent.GetInteractionQuestion(interactQuestionDTO));
//                ImageUtils.loadImage(MainActivity.this, pojo.getAvatarUrl(), ivLogo, true);
            }

            @Override
            public void onFailure(Call<InteractQuestionDTO> call, Throwable t)
            {
            }
        });


    }


    public void onEventMainThread(QuestionEvent.GetInteractionQuestion event) throws SQLException
    {
        InteractQuestionDTO interactQuestionDTO = event.getInteractQuestionDTO();
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
        MediaController videoControl = new MediaController(this);
        videoControl.setAnchorView(videoView);
        videoView.setMediaController(videoControl);
        videoView.requestFocus();
        currentVideo=0;
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                customProgressDialog.dismiss("");
                playVideo(videoUrls.get(currentVideo));
            }
        });
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
//        {
//            @Override
//            public void onCompletion(MediaPlayer mp)
//            {
//                currentVideo++;
//                Toast.makeText(MainActivity.this, "done video -next video: " + currentVideo, Toast.LENGTH_SHORT).show();
//                if (currentVideo > videoUrls.size() - 1)
//                {
//                    videoView.stopPlayback();
//                }
//                playVideo(videoUrls.get(currentVideo));
//            }
//        });
    }

    private void playVideo(String urlVideo)
    {
        Log.i("url--------", urlVideo);
        Uri vidUri = Uri.parse(urlVideo);
        videoView.setVideoURI(vidUri);
        videoView.start();
    }

}
