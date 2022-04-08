package com.example.alber.pocketapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5,bitmap6;
    Handler handler;
    int what;
    String QQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText loginusername = findViewById(R.id.ed_mainactivity_username);
        final EditText logingpassword = findViewById(R.id.ed_mainactivity_password);
        final ImageView icon = findViewById(R.id.iv_mainactivity_icon);
        final ImageView clear = findViewById(R.id.btn_mainactivity_clear);
        Button login = findViewById(R.id.btn_mainactivity_login);
        CheckBox hide = findViewById(R.id.cb_mainactivity_hide);
        loginusername.setText("张春晓");
        logingpassword.setText("123");

        //消息提示框
        dialog();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginusername.setText("");
            }
        });

        loginusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                final String username = loginusername.getText().toString();
                final AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
                alphaAnimation.setDuration(300);

//                获取QQ头像的URL
//                new Thread(){
//                    @Override
//                    public void run() {
//                        try {
//                            if (username.equals("李成玮")) {
//                                URL url = new URL("http://q1.qlogo.cn/g?b=qq&nk=1165461458&s=640");
//                                handler.sendEmptyMessage(0x001);
//                                InputStream is = url.openStream();
//                                bitmap = BitmapFactory.decodeStream(is);
//                                is.close();
//                                is = url.openStream();
//                                OutputStream os = openFileOutput("logo.jpg",MODE_PRIVATE);
//                                byte[] buff = new byte[1024];
//                                int hasRead = 0;
//                                while ((hasRead = is.read(buff)) > 0){
//                                    os.write(buff,0,hasRead);
//                                }
//                                is.close();
//                                os.close();
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
//
                handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        if(msg.what == 0x001){
                            icon.setImageBitmap(bitmap1);
                            icon.setAnimation(alphaAnimation);
                        }
                        else if (msg.what == 0x002){
                            icon.setImageBitmap(bitmap2);
                            icon.setAnimation(alphaAnimation);
                        }
                        else if (msg.what == 0x003){
                            icon.setImageBitmap(bitmap3);
                            icon.setAnimation(alphaAnimation);
                        }
                        else if (msg.what == 0x004){
                            icon.setImageBitmap(bitmap4);
                            icon.setAnimation(alphaAnimation);
                        }
                        else if (msg.what == 0x005){
                            icon.setImageBitmap(bitmap5);
                            icon.setAnimation(alphaAnimation);
                        }
                        else if (msg.what == 0x006){
                            icon.setImageBitmap(bitmap6);
                            icon.setAnimation(alphaAnimation);
                        }
                        else if (msg.what == 0x007){
                            icon.setImageBitmap(bitmap6);
                            icon.setAnimation(alphaAnimation);
                        }
                    }
                };

                if(username.equals("李成玮")){
                    QQ = "1165461458";
                    what = 0x001;
                    massage(QQ,what);
                }
                else if(username.equals("雷星豪")){
                    QQ = "2377175062";
                    what = 0x002;
                    massage(QQ,what);
                }
                else if(username.equals("卢珣")){
                    QQ = "1419965034";
                    what = 0x003;
                    massage(QQ,what);
                }
                else if(username.equals("周鲲宇")){
                    QQ = "785744860";
                    what = 0x004;
                    massage(QQ,what);
                }
                else if(username.equals("易紫君")){
                    QQ = "1936141710";
                    what = 0x005;
                    massage(QQ,what);
                }
                else if(username.equals("张婧卓")){
                    QQ = "3370708380";
                    what = 0x006;
                    massage(QQ,what);
                }
                else if(username.equals("张春晓")){
                    QQ = "2692785841";
                    what = 0x007;
                    massage(QQ,what);
                }
                else {
                    icon.setImageResource(R.drawable.app_logo);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle bundle = new Bundle();
                final String username = loginusername.getText().toString();
                final String password = logingpassword.getText().toString();
                if(username.equals("李成玮") && password.equals("16275225")){
                    jump();
                }
                else if(username.equals("雷星豪") && password.equals("16275211")){
                    jump();
                }
                else if(username.equals("卢珣") && password.equals("16275230")){
                    jump();
                }
                else if(username.equals("周鲲宇") && password.equals("16275220")){
                    jump();
                }
                else if(username.equals("张婧卓") && password.equals("16275233")){
                    jump();
                }
                else if(username.equals("易紫君") && password.equals("16275204")){
                    jump();
                }
                else if(username.equals("张春晓") && password.equals("17161336")){
                    jump();
                }
                else {
                    Toast.makeText(MainActivity.this,"请重新输入用户名和密码" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        logingpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    logingpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else{
                    logingpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void jump(){
        EditText loginusername = findViewById(R.id.ed_mainactivity_username);
        EditText logingpassword = findViewById(R.id.ed_mainactivity_password);
        String username = loginusername.getText().toString();
        String password = logingpassword.getText().toString();
        Bundle user = new Bundle();
        user.putString("user",username);
        user.putString("pass",password);
        Toast.makeText(MainActivity.this, username + "登陆成功" ,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.putExtras(user);
        startActivity(intent);
        onRestart();
    }

    private void dialog(){
        Bundle bundle = getIntent().getExtras();
        int i = bundle.getInt("i");
        if (i == 1){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setIcon(R.drawable.app_logo);
            dialog.setTitle("掌上APP！干！");
            dialog.setMessage(
                    "欢迎使用掌上APP\n以下为本软件的开发人员\n李成玮\n雷星豪\n卢珣\n周鲲宇\n张婧卓\n易紫君\n*排名不分先后*"
            );
            dialog.setPositiveButton("同意", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this,"您已同意上述内容！",Toast.LENGTH_LONG).show();
                }
            });
            dialog.show();
        }else {

        }
    }

    private void massage(final String QQ,final int what){
        new Thread(){
            @Override
            public void run() {
                try{
                    URL url = new URL("http://q1.qlogo.cn/g?b=qq&nk=" + QQ + "&s=640");
                    handler.sendEmptyMessage(what);
                    InputStream is = url.openStream();

                    if (QQ.equals("1165461458")){
                        bitmap1 = BitmapFactory.decodeStream(is);
                    }
                    else if (QQ.equals("2377175062")){
                        bitmap2 = BitmapFactory.decodeStream(is);
                    }
                    else if (QQ.equals("1419965034")){
                        bitmap3 = BitmapFactory.decodeStream(is);
                    }
                    else if (QQ.equals("785744860")){
                        bitmap4 = BitmapFactory.decodeStream(is);
                    }
                    else if (QQ.equals("1936141710")){
                        bitmap5 = BitmapFactory.decodeStream(is);
                    }
                    else if (QQ.equals("3370708380")){
                        bitmap6 = BitmapFactory.decodeStream(is);
                    }
                    else if (QQ.equals("2692785841")){
                        bitmap6 = BitmapFactory.decodeStream(is);
                    }

                    is.close();
                    is = url.openStream();
                    OutputStream os = openFileOutput("logo.jpg",MODE_PRIVATE);
                    byte[] buff = new byte[1024];
                    int hasRead = 0;
                    while ((hasRead = is.read(buff)) > 0){
                        os.write(buff,0,hasRead);
                    }
                    is.close();
                    os.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }

}
