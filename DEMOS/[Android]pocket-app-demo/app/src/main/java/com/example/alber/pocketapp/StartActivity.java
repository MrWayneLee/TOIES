package com.example.alber.pocketapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//        if(Build.VERSION.SDK_INT >= 21){
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.argb(00,255,255,255));
//        }

        final ImageView imageView = findViewById(R.id.im_start_image);
        imageView.setImageResource(R.drawable.image_start);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(StartActivity.this,"你的名字  我的心事",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                final AlphaAnimation animation1 = new AlphaAnimation(1,0);
                animation1.setDuration(500);
                animation1.setFillAfter(true);
                animation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        jump();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.startAnimation(animation1);
                    }
                },1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump();
            }
        });

        imageView.startAnimation(alphaAnimation);

        }

        public void jump(){
            int i = 1;
            Bundle bundle = new Bundle();
            bundle.putInt("i",i);
            Intent intent = new Intent(StartActivity.this,MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

}


