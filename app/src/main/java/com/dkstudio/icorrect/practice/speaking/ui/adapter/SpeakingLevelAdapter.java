package com.dkstudio.icorrect.practice.speaking.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dkstudio.icorrect.R;
import com.dkstudio.icorrect.practice.speaking.ui.fragment.SpeakingLevelFragment;
import com.dkstudio.icorrect.practice.speaking.ui.model.SpeakingLevelModel;

import java.util.List;

/**
 * Created by Administrator on 03/07/2016.
 */
public class SpeakingLevelAdapter extends RecyclerView.Adapter<SpeakingLevelAdapter.MyViewHolder>
{
    public List<SpeakingLevelModel> speakingLevelModelList;
    Context context;

    public SpeakingLevelAdapter(List<SpeakingLevelModel> speakingLevelModelList,Context context)
    {
        this.speakingLevelModelList = speakingLevelModelList;
        this.context=context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvTitle, tvDescription;
        public CardView cardView;

        public MyViewHolder(View view)
        {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.speaking_level_item, parent, false);

        return new MyViewHolder(itemVIew);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        SpeakingLevelModel speakingLevelModel = speakingLevelModelList.get(position);
        holder.tvTitle.setText(speakingLevelModel.getTitle());
        holder.tvDescription.setText(speakingLevelModel.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return speakingLevelModelList.size();
    }

}
