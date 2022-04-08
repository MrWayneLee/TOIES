package com.example.alber.pocketapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_contact extends Fragment {

    String[] name = new String[]{
            "李成玮",
            "李陈程",
            "曹悦",
            "梅玉竹",
            "余湉",
            "张雨婷"
    };

    String[] qq = new String[]{
            "1165461458",
            "2377175062",
            "1419965034",
            "785744860",
            "3370708380",
            "1936141710"
    };

    String[] phonenumber = new String[]{
            "13040936774",
            "17371672130",
            "17612705721",
            "17671772837",
            "13125119812",
            "13617221263"
    };

    int[] photo = new int[]{
            R.drawable.logo_my,
            R.drawable.logo_leixinghao,
            R.drawable.logo_luxun,
            R.drawable.logo_zhoukunyu,
            R.drawable.logo_zhangjinzhuo,
            R.drawable.logo_yizijun
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);

        final List<Map<String,String>> Data = new ArrayList<>();

        HashMap<String,String> item1 = new HashMap<>();
        item1.put("name",name[0]);
        item1.put("qq",qq[0]);
        item1.put("phonenumber",phonenumber[0]);
        item1.put("photo", String.valueOf(photo[0]));
        HashMap<String,String> item2 = new HashMap<>();
        item2.put("name",name[1]);
        item2.put("qq",qq[1]);
        item2.put("phonenumber",phonenumber[1]);
        item2.put("photo", String.valueOf(photo[1]));
        HashMap<String,String> item3 = new HashMap<>();
        item3.put("name",name[2]);
        item3.put("qq",qq[2]);
        item3.put("phonenumber",phonenumber[2]);
        item3.put("photo", String.valueOf(photo[2]));
        HashMap<String,String> item4 = new HashMap<>();
        item4.put("name",name[3]);
        item4.put("qq",qq[3]);
        item4.put("phonenumber",phonenumber[3]);
        item4.put("photo", String.valueOf(photo[3]));
        HashMap<String,String> item5 = new HashMap<>();
        item5.put("name",name[4]);
        item5.put("qq",qq[4]);
        item5.put("phonenumber",phonenumber[4]);
        item5.put("photo", String.valueOf(photo[4]));
        HashMap<String,String> item6 = new HashMap<>();
        item6.put("name",name[5]);
        item6.put("qq",qq[5]);
        item6.put("phonenumber",phonenumber[5]);
        item6.put("photo", String.valueOf(photo[5]));

        Data.add(item1);
        Data.add(item2);
        Data.add(item3);
        Data.add(item4);
        Data.add(item5);
        Data.add(item6);

        ListView contact = view.findViewById(R.id.lv_indexcontact_item);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),Data,R.layout.fragment_contact_item,
                new String[]{"name","qq","phonenumber","photo"},
                new int[]{
                        R.id.tv_fragment_contact_item_name,
                        R.id.tv_fragment_contact_item_QQ,
                        R.id.tv_fragment_contact_item_phone,
                        R.id.iv_fragment_contact_item_icon,
                });
        contact.setAdapter(simpleAdapter);

        contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView call = view.findViewById(R.id.iv_fragment_contact_item_call);
                final Intent jumpcall = new Intent();
                final HashMap<String,String> item = (HashMap<String, String>) Data.get(position);
                switch (position){
                    case 0:
                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumpcall.setAction(Intent.ACTION_DIAL);
                                jumpcall.setData(Uri.parse("tel:"+item.get("phonenumber")));
                                startActivity(jumpcall);
                            }
                        });
                        break;
                    case 1:
                    call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumpcall.setAction(Intent.ACTION_DIAL);
                                jumpcall.setData(Uri.parse("tel:"+item.get("phonenumber")));
                                startActivity(jumpcall);
                            }
                        });
                        break;
                    case 2:
                    call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumpcall.setAction(Intent.ACTION_DIAL);
                                jumpcall.setData(Uri.parse("tel:"+item.get("phonenumber")));
                                startActivity(jumpcall);
                            }
                        });
                        break;
                    case 3:
                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumpcall.setAction(Intent.ACTION_DIAL);
                                jumpcall.setData(Uri.parse("tel:"+item.get("phonenumber")));
                                startActivity(jumpcall);
                            }
                        });
                        break;
                    case 4:
                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumpcall.setAction(Intent.ACTION_DIAL);
                                jumpcall.setData(Uri.parse("tel:"+item.get("phonenumber")));
                                startActivity(jumpcall);
                            }
                        });
                        break;
                    case 5:
                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumpcall.setAction(Intent.ACTION_DIAL);
                                jumpcall.setData(Uri.parse("tel:"+item.get("phonenumber")));
                                startActivity(jumpcall);
                            }
                        });
                        break;
                }
            }
        });

        return  view;
    }
}
