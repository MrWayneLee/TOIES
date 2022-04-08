package com.example.alber.pocketapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_index_notice_notice extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_index_notice_notice,container,false);

        String[] time = new String[]{
                "2018-11-11",
                "2018-11-13"
        };

        String[] title = new String[]{
                "双十一不停电通知",
                "恢复断电通知"
        };
        String[] context = new String[]{
                "双十一淘宝大促销期间，23：00晚间将不停电，望各位同学周知。",
                "从2018年11月13日之后，23：00晚间之后将恢复断电，望各位同学周知"
        };

        final List<Map<String,String>> Data = new ArrayList<>();

        final HashMap<String,String> item1 = new HashMap<>();
        item1.put("time",time[0]);
        item1.put("title",title[0]);
        item1.put("context",context[0]);
        HashMap<String,String> item2 = new HashMap<>();
        item2.put("time",time[1]);
        item2.put("title",title[1]);
        item2.put("context",context[1]);

        Data.add(item1);
        Data.add(item2);

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),Data,R.layout.fragment_index_notice_item,
                new String[]{"time","title"},
                new int[]{R.id.tv_fragment_index_notice_time,R.id.tv_fragment_index_notice_content});
        ListView notice = view.findViewById(R.id.lv_fragmentindex_notice);
        notice.setAdapter(simpleAdapter);

        notice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> item = (HashMap<String, String>) Data.get(position);
                Bundle notice = new Bundle();
                notice.putInt("code",1);
                Intent intent = new Intent(getActivity(),Fragment_index_notice_contentActivity.class);
                switch (position){
                    case 0:
                        String title = item.get("title");
                        String time = item.get("time");
                        String context = item.get("context");
                        notice.putString("title",title);
                        notice.putString("time",time);
                        notice.putString("context",context);
                        intent.putExtras(notice);
                        startActivity(intent);
                        break;
                    case 1:
                        String title2 = item.get("title");
                        String time2 = item.get("time");
                        String context2 = item.get("context");
                        notice.putString("title",title2);
                        notice.putString("time",time2);
                        notice.putString("context",context2);
                        intent.putExtras(notice);
                        startActivity(intent);
                        break;
                }
            }
        });

        return view;
    }
}
