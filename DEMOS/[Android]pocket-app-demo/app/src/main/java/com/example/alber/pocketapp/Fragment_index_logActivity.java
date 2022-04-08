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

public class Fragment_index_logActivity extends AppCompatActivity {

    String[] time = new String[]{
            "2018-11-1",
            "2018-11-2",
            "2018-11-3",
            "2018-11-4",
            "2018-11-5"
    };

    String[] title = new String[]{
            "2018-11-1的日志",
            "2018-11-2的日志",
            "2018-11-3的日志",
            "2018-11-4的日志",
            "2018-11-5的日志"
    };

    String[] context = new String[]{
            "   第一天，在我的项目中添加了一个开场动画。使用了使用了AlphaAnimation函数，用setDuration设置动画持续时间，单位为ms。用1.0或0.1的值来设置淡入或淡出。特别简单的一个动画函数\n" +
            "   当然还有很多类似于Animation的函数。以后再去研究吧。",

            "   这是做项目的第二天，简单的美化了一下UI和内容\n" +
            "   在网上查阅了一下关于EditText、TextView和Button以及其他的样式代码，自己也尝试了一下，设计出了这个APP的主色调和UI。特别丑，但没时间想这么多了，赶紧先完成其他的部分吧。",

            "   今天把我逼疯了，终于稍微搞懂了一下BottomNavigationView + Fragment的结构，添加了4个页面。真的，说复杂也不复杂，说不复杂有感觉很复杂，一个很纠结的控件。还有这么多的页面...睡觉先\n" +
            "   还是优化了UI，添加了关于界面，想了下ListView，明天在实施。",

            "   ListView还行，创建倒是挺简单的，一晃就过了N天了，不想再记录日期了...流水账挺好的\n" +
            "   在传数据的时候遇到了一点点小问题，不过最终还是解决了。\n" +
            "   想添加一个Dialog获取信息然后传到About页面",

            "   好好睡一觉吧，今天什么都不想！一天不想碰代码，反正还有这么多日子...妈卖批PHP到结尾的时候才安排上课程，什么鬼...但至少总有点事情做吧。"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_index_log);

        final List<Map<String,String>> Data = new ArrayList<>();

        HashMap<String,String> item1 = new HashMap<>();
        item1.put("time",time[0]);
        item1.put("title",title[0]);
        item1.put("context",context[0]);
        HashMap<String,String> item2 = new HashMap<>();
        item2.put("time",time[1]);
        item2.put("title",title[1]);
        item2.put("context",context[1]);
        HashMap<String,String> item3 = new HashMap<>();
        item3.put("time",time[2]);
        item3.put("title",title[2]);
        item3.put("context",context[2]);
        HashMap<String,String> item4 = new HashMap<>();
        item4.put("time",time[3]);
        item4.put("title",title[3]);
        item4.put("context",context[3]);
        HashMap<String,String> item5 = new HashMap<>();
        item5.put("time",time[4]);
        item5.put("title",title[4]);
        item5.put("context",context[4]);

        Data.add(item1);
        Data.add(item2);
        Data.add(item3);
        Data.add(item4);
        Data.add(item5);

        final ImageView back = findViewById(R.id.btn_fragmentindexlog_back);
        Button add = findViewById(R.id.btn_fragmentindexlog_add);
        ListView log = findViewById(R.id.lv_fragmentlog_item);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,Data,R.layout.fragment_index_log_item,
                new String[]{"time","title"},
                new int[]{R.id.tv_fragment_index_log_item_time,R.id.tv_fragment_index_log_item_title});
        log.setAdapter(simpleAdapter);

        log.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> item = (HashMap<String, String>) Data.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(Fragment_index_logActivity.this,Fragment_index_log_contentActivity.class);
                switch (position){
                    case 0:
                        String title = item.get("title");
                        String context = item.get("context");
                        bundle.putString("title",title);
                        bundle.putString("context",context);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        String title1 = item.get("title");
                        String context1 = item.get("context");
                        bundle.putString("title",title1);
                        bundle.putString("context",context1);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 2:
                        String title2 = item.get("title");
                        String context2 = item.get("context");
                        bundle.putString("title",title2);
                        bundle.putString("context",context2);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 3:
                        String title3 = item.get("title");
                        String context3 = item.get("context");
                        bundle.putString("title",title3);
                        bundle.putString("context",context3);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 4:
                        String title4 = item.get("title");
                        String context4 = item.get("context");
                        bundle.putString("title",title4);
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
                Intent add = new Intent(Fragment_index_logActivity.this,Fragment_index_log_addActivity.class);
                startActivity(add);
            }
        });
    }
}
