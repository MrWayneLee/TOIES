package com.example.alber.pocketapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_index_news_contentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_index_news_content);

        ImageView back = findViewById(R.id.btn_fragmentindexnewscontent_back);
        TextView title = findViewById(R.id.tv_fragmentindexnewscontent_title);
        TextView time = findViewById(R.id.tv_fragmentindexnewscontent_time);
        TextView context = findViewById(R.id.tv_fragmentindexnewscontent_content);

        Bundle news = getIntent().getExtras();
        String newstitle = news.getString("title");
        String newstime = news.getString("time");
        String newscontent = news.getString("context");
        title.setText(newstitle);
        time.setText(newstime);
        context.setText(newscontent);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
