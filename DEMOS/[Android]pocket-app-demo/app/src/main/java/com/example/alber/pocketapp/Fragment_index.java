package com.example.alber.pocketapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Fragment_index extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false);

        TextView news = view.findViewById(R.id.tv_fragmentindex_news);
        TextView music = view.findViewById(R.id.tv_fragmentindex_music);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Fragment_index_newsActivity.class);
                startActivity(intent);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Fragment_index_MusicActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout schedule = view.findViewById(R.id.rl_schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toschedule = new Intent(getActivity(),Fragment_index_scheduleActivity.class);
                startActivity(toschedule);
            }
        });

        RelativeLayout list = view.findViewById(R.id.rl_list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tolist = new Intent(getActivity(),Fragment_index_noticeActivity.class);
                startActivity(tolist);
            }
        });

        RelativeLayout log = view.findViewById(R.id.rl_log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tolog = new Intent(getActivity(),Fragment_index_logActivity.class);
                startActivity(tolog);
            }
        });

        RelativeLayout note = view.findViewById(R.id.rl_note);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tonote = new Intent(getActivity(),Fragment_index_noteActivity.class);
                startActivity(tonote);
            }
        });

    }
}
