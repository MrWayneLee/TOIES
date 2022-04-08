package com.example.alber.pocketapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fragment_index_schedule_today extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_index_schedule_today,container,false);

        String[] data = new String[]{
                "10：30  PHP课程",
                "14：00  PHP课程",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " ",
                " "
        };

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,data);
        ListView today = view.findViewById(R.id.iv_fragmentindexschedule_today);
        today.setAdapter(arrayAdapter);
        return view;
    }
}
