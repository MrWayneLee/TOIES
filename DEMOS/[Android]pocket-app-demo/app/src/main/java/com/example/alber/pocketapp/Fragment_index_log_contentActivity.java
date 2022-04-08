package com.example.alber.pocketapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_index_log_contentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_index_log_content);

        ImageView back = findViewById(R.id.btn_fragmentindexlogcontent_back);
        TextView title = findViewById(R.id.tv_fragmentindexlogcontent_title);
        TextView content = findViewById(R.id.tv_fragmentindexlogcontent_content);

        Bundle bundle = getIntent().getExtras();
        String titlecontent = bundle.getString("title");
        String context= bundle.getString("context");
        content.setText(context);
        title.setText(titlecontent);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
