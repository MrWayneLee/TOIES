package com.example.alber.pocketapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_index_notice_contentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_index_notice_content);

        TextView maintitle = findViewById(R.id.fragmentindexnoticecontent_title);
        ImageView back = findViewById(R.id.btn_fragmentindexnoticecontent_back);
        TextView title = findViewById(R.id.tv_fragmentindexnoticecontent_title);
        TextView time = findViewById(R.id.tv_fragmentindexnoticecontent_time);
        TextView context = findViewById(R.id.tv_fragmentindexnoticecontent_content);

        int bundle = getIntent().getExtras().getInt("code");
        if (bundle == 1){
            maintitle.setText("通   知   内   容");
            Bundle notice = getIntent().getExtras();
            String noticetitle = notice.getString("title");
            String noticetime = notice.getString("time");
            String noticecontext = notice.getString("context");
            title.setText(noticetitle);
            time.setText(noticetime);
            context.setText(noticecontext);
        }
        else if (bundle == 2){
            maintitle.setText("公   告   内   容");
            Bundle announcement = getIntent().getExtras();
            String announcementtitle = announcement.getString("title");
            String announcementtime = announcement.getString("time");
            String announcementcontext = announcement.getString("context");
            title.setText(announcementtitle);
            time.setText(announcementtime);
            context.setText(announcementcontext);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
