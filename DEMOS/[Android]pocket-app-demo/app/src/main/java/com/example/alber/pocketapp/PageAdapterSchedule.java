package com.example.alber.pocketapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;

public class PageAdapterSchedule extends FragmentPagerAdapter {
    private int num;
    private HashMap<Integer,Fragment> FragmentMap = new HashMap<>();

    public PageAdapterSchedule(FragmentManager fm , int num){
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int i) {
        return createFragment(i);
    }

    @Override
    public int getCount() {
        return num;
    }

    private Fragment createFragment(int pos){
        Fragment fragment = FragmentMap.get(pos);

        if (fragment ==null){
            switch (pos){
                case 0:
                    fragment = new Fragment_index_schedule_today();
                    break;
                case 1:
                    fragment = new Fragment_index_schedule_tomorrow();
                    break;
                case 2:
                    fragment = new Fragment_index_schedule_history();
                    break;
            }
            FragmentMap.put(pos,fragment);
        }
        return fragment;
    }
}
