package com.dkstudio.icorrect.practice.speaking.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.dkstudio.icorrect.R;

/**
 * Created by Administrator on 03/07/2016.
 */
public class SpeakingDetailFragment extends FragmentActivity
{
    public static SpeakingDetailFragment instance()
    {
        SpeakingDetailFragment speakingDetailFragment = new SpeakingDetailFragment();
        return speakingDetailFragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaking_detail_fragment);

    }
}
