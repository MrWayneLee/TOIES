package com.example.alber.pocketapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_index_newsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_index_news);

        int[] image = new int[]{
                R.drawable.logo_yizijun,
                R.drawable.logo_zhangjinzhuo
        };

        String[] title = new String[]{
                "新闻标题1",
                "新闻标题2"
        };

        String[] subtitle = new String[]{
                "新闻副标题1",
                "新闻副标题2"
        };

        String[] time = new String[]{
                "2018-11-11",
                "2018-11-13",
        };

        String[] newscontent = new String[]{
                "新闻内容1",
                "新闻内容2"
        };

        final List<Map<String,String>> Data = new ArrayList<>();

        HashMap<String,String> item1 = new HashMap<>();
        item1.put("title",title[0]);
        item1.put("subtitle",subtitle[0]);
        item1.put("time",time[0]);
        item1.put("news",newscontent[0]);
        item1.put("image",String.valueOf(image[0]));

        HashMap<String,String> item2 = new HashMap<>();
        item2.put("title",title[1]);
        item2.put("subtitle",subtitle[1]);
        item2.put("time",time[1]);
        item2.put("news",newscontent[1]);
        item2.put("image",String.valueOf(image[1]));

        Data.add(item1);
        Data.add(item2);

        ImageView back = findViewById(R.id.btn_fragmentindexnews_back);
        ListView news = findViewById(R.id.lv_fragmentindexnews_content);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,Data,R.layout.fragment_index_news_item,
                new String[]{"title","subtitle","time","image"},
                new int[]{
                        R.id.tv_fragment_index_news_title,
                        R.id.tv_fragment_index_news_subtitle,
                        R.id.tv_fragment_index_news_time,
                        R.id.lv_fragment_index_news_image
        });
        news.setAdapter(simpleAdapter);

        news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> item = (HashMap<String, String>) Data.get(position);
                Intent intent = new Intent(Fragment_index_newsActivity.this,Fragment_index_news_contentActivity.class);
                Bundle bundle = new Bundle();
                switch (position){
                    case 0:
                        String title = item.get("subtitle");
                        String time = item.get("time");
                        String newscontext = item.get("news");
                        bundle.putString("title",title);
                        bundle.putString("time",time);
                        bundle.putString("context",newscontext);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        String title2 = item.get("subtitle");
                        String time2 = item.get("time");
                        String newscontext2 = item.get("news");
                        bundle.putString("title",title2);
                        bundle.putString("time",time2);
                        bundle.putString("context",newscontext2);
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
    }
}
