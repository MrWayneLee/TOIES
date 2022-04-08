package com.example.alber.pocketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_index_noteActivity extends AppCompatActivity {

    String[] time = new String[]{
            "2018-11-1",
            "2018-11-2",
            "2018-11-3",
            "2018-11-4",
            "2018-11-5"
    };

    String[] note = new String[]{
            "2018-11-1的便利贴",
            "2018-11-2的便利贴",
            "2018-11-3的便利贴",
            "2018-11-4的便利贴",
            "2018-11-5的便利贴"
    };

    String[] context = new String[]{
            "",
            "",
            "",
            "",
            ""
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_index_note);

        final List<Map<String,String>> Data = new ArrayList<>();

        Map<String,String> item1 = new HashMap<>();
        item1.put("time",time[0]);
        item1.put("note",note[0]);
        item1.put("context",context[0]);
        Map<String,String> item2 = new HashMap<>();
        item2.put("time",time[1]);
        item2.put("note",note[1]);
        item2.put("context",context[1]);
        Map<String,String> item3 = new HashMap<>();
        item3.put("time",time[2]);
        item3.put("note",note[2]);
        item3.put("context",context[2]);
        Map<String,String> item4 = new HashMap<>();
        item4.put("time",time[3]);
        item4.put("note",note[3]);
        item4.put("context",context[3]);
        Map<String,String> item5 = new HashMap<>();
        item5.put("time",time[4]);
        item5.put("note",note[4]);
        item5.put("context",context[4]);

        Data.add(item1);
        Data.add(item2);
        Data.add(item3);
        Data.add(item4);
        Data.add(item5);


        ImageView back = findViewById(R.id.btn_fragmentindexnote_back);
        Button add = findViewById(R.id.btn_fragmentindexnote_add);
        ListView note = findViewById(R.id.lv_fragmentnote_item);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,Data,R.layout.fragment_index_note_item,
                new String[]{"time","note"},
                new int[]{R.id.tv_fragment_index_note_item_time,R.id.tv_fragment_index_note_item_title});
        note.setAdapter(simpleAdapter);

        note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> item = (HashMap<String, String>) Data.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(Fragment_index_noteActivity.this,Fragment_index_note_contentActivity.class);
                switch (position){
                    case 0:
                        String time = item.get("time");
                        String context = item.get("context");
                        bundle.putString("time",time);
                        bundle.putString("context",context);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        String time1 = item.get("time");
                        String context1 = item.get("context");
                        bundle.putString("time",time1);
                        bundle.putString("context",context1);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 2:
                        String time2 = item.get("time");
                        String context2 = item.get("context");
                        bundle.putString("time",time2);
                        bundle.putString("context",context2);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 3:
                        String time3 = item.get("time");
                        String context3 = item.get("context");
                        bundle.putString("time",time3);
                        bundle.putString("context",context3);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 4:
                        String time4 = item.get("time");
                        String context4 = item.get("context");
                        bundle.putString("time",time4);
                        bundle.putString("context",context4);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(Fragment_index_noteActivity.this,Fragment_index_note_addActivity.class);
                startActivity(add);
            }
        });

    }
}
