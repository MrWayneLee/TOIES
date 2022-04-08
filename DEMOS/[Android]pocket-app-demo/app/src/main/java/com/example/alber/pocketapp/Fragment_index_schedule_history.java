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

import java.util.zip.Inflater;

public class Fragment_index_schedule_history extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_index_schedule_history,container,false);

        String[] data = new String[]{
                "2018-11-1 的日程",
                "2018-11-2 的日程",
                "2018-11-3 的日程",
                "2018-11-4 的日程",
                "2018-11-5 的日程",
                "2018-11-6 的日程",
                "2018-11-7 的日程",
                "2018-11-8 的日程",
                "2018-11-9 的日程",
                "2018-11-10的日程",
                "2018-11-11的日程",
                "2018-11-12的日程",
        };

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,data);
        ListView history = view.findViewById(R.id.iv_fragmentindexschedule_history);
        history.setAdapter(arrayAdapter);
        return view;
    }
}
