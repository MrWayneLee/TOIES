package com.example.alber.pocketapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_index_note_contentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_index_note_content);

        ImageView back = findViewById(R.id.btn_fragmentindexnotecontent_back);
        TextView title = findViewById(R.id.tv_fragmentindexnotecontent_title);
        TextView context = findViewById(R.id.tv_fragmentindexnotecontent_content);

        Bundle bundle = getIntent().getExtras();
        String notetime = bundle.getString("time");
        String content = bundle.getString("context");
        title.setText(notetime);
        context.setText(content);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
