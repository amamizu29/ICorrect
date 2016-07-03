package com.dkstudio.icorrect.practice.speaking.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dkstudio.icorrect.R;

/**
 * Created by Administrator on 02/07/2016.
 */
public class AboutFragment extends Fragment {

    String text;

    public static AboutFragment instance(String text) {
        AboutFragment homeFragment = new AboutFragment();
        homeFragment.setText(text);
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_fragment, container, false);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
