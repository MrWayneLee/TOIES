package com.example.alber.pocketapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Fragment_about extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_about,container,false);

        Bundle bundle = getActivity().getIntent().getExtras();
        String username = bundle.getString("user");
        String pass = bundle.getString("pass");

        Button btn_about = view.findViewById(R.id.btn_aboutavtivity_about);
        RelativeLayout rl = view.findViewById(R.id.rl_fragmentabout_background);
        rl.setBackgroundResource(R.drawable.fragment_background);
        final ImageView icon = view.findViewById(R.id.lv_about_logo);
        TextView user_name = view.findViewById(R.id.tv_about_user);

        if(username.equals("李成玮")){
            icon.setImageResource(R.drawable.logo_my);
            user_name.setText(username + "  " + pass);
        }
        else if(username.equals("雷星豪")){
            icon.setImageResource(R.drawable.logo_leixinghao);
            user_name.setText(username + "  " + pass);
        }
        else if(username.equals("卢珣")){
            icon.setImageResource(R.drawable.logo_luxun);
            user_name.setText(username + "  " + pass);
        }
        else if(username.equals("周鲲宇")){
            icon.setImageResource(R.drawable.logo_zhoukunyu);
            user_name.setText(username + "  " + pass);
        }
        else if(username.equals("易紫君")){
            icon.setImageResource(R.drawable.logo_yizijun);
            user_name.setText(username + "  " + pass);
        }
        else if(username.equals("张婧卓")){
            icon.setImageResource(R.drawable.logo_zhangjinzhuo);
            user_name.setText(username + "  " + pass);
        }
        else if(username.equals("张春晓")){
            icon.setImageResource(R.drawable.logo_mylove);
            user_name.setText(username + "  " + pass);
        }

//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                if(msg.what == 0x001){
//                    icon.setImageBitmap(bitmap1);
//                }
//                else if (msg.what == 0x002){
//                    icon.setImageBitmap(bitmap2);
//                }
//                else if (msg.what == 0x003){
//                    icon.setImageBitmap(bitmap3);
//                }
//                else if (msg.what == 0x004){
//                    icon.setImageBitmap(bitmap4);
//                }
//                else if (msg.what == 0x005){
//                    icon.setImageBitmap(bitmap5);
//                }
//                else if (msg.what == 0x006){
//                    icon.setImageBitmap(bitmap6);
//                }
//                else if (msg.what == 0x007){
//                    icon.setImageBitmap(bitmap7);
//                }
//            }
//        };


        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Fragment_about_usActivity.class);
                startActivityForResult(intent,1);
            }
        });

        Button btn_logout = view.findViewById(R.id.btn_aboutavtivity_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

//    private void massage(final String QQ,final int what){
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("http://q1.qlogo.cn/g?b=qq&nk=" + QQ + "&s=640");
//                    handler.sendEmptyMessage(what);
//                    InputStream is = url.openStream();
//
//                    if (QQ.equals("1165461458")){
//                        bitmap1 = BitmapFactory.decodeStream(is);
//                    }
//                    else if (QQ.equals("2377175062")){
//                        bitmap2 = BitmapFactory.decodeStream(is);
//                    }
//                    else if (QQ.equals("1419965034")){
//                        bitmap3 = BitmapFactory.decodeStream(is);
//                    }
//                    else if (QQ.equals("785744860")){
//                        bitmap4 = BitmapFactory.decodeStream(is);
//                    }
//                    else if (QQ.equals("1936141710")){
//                        bitmap5 = BitmapFactory.decodeStream(is);
//                    }
//                    else if (QQ.equals("3370708380")){
//                        bitmap6 = BitmapFactory.decodeStream(is);
//                    }
//                    else if (QQ.equals("1962045419")){
//                        bitmap7 = BitmapFactory.decodeStream(is);
//                    }
//
//                    is.close();
//                    is = url.openStream();
//                    OutputStream os = getContext().openFileOutput("logo.jpg",MODE_PRIVATE);
//                    byte[] buff = new byte[1024];
//                    int hasRead = 0;
//                    while ((hasRead = is.read(buff)) > 0){
//                        os.write(buff,0,hasRead);
//                    }
//                    is.close();
//                    os.close();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        };
}
