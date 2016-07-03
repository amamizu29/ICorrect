package com.dkstudio.icorrect.practice.speaking.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dkstudio.icorrect.R;
import com.dkstudio.icorrect.practice.speaking.ui.adapter.SpeakingLevelAdapter;
import com.dkstudio.icorrect.practice.speaking.ui.model.SpeakingLevelModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 02/07/2016.
 */
public class SpeakingLevelFragment extends Fragment {

    RecyclerView recyclerView;
    SpeakingLevelAdapter speakingLevelAdapter;
    List<SpeakingLevelModel> speakingLevelModelList;

    public static SpeakingLevelFragment instance() {
        SpeakingLevelFragment speakingLevelFragment = new SpeakingLevelFragment();
        return speakingLevelFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //TODO fake data
        speakingLevelModelList=new ArrayList<>();
        speakingLevelModelList.add(new SpeakingLevelModel("Part I","You answer questions about yourself, your family, your work and your interests."));
        speakingLevelModelList.add(new SpeakingLevelModel("Part II","You speak about a topic. You will be given a task card which asks you to speak about a particular topic and includes points that you can cover in your talk. You will be given 1 minute to prepare your talk. You will then speak for 1-2 minutes."));
        speakingLevelModelList.add(new SpeakingLevelModel("Part III","You have a longer discussion on the topic. The examiner will ask you further questions connected to the topic in Part 2."));
        speakingLevelModelList.add(new SpeakingLevelModel("Part II & III","You will take test of part 2 and part 3 with same topic"));
        speakingLevelModelList.add(new SpeakingLevelModel("Full Test","You will test all of parts in IELST Speaking. During this test, you can stop and reanswer question."));
        speakingLevelAdapter=new SpeakingLevelAdapter(speakingLevelModelList);

        recyclerView= (RecyclerView) view.findViewById(R.id.speakingLevelRecyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(speakingLevelAdapter);
    }

}
