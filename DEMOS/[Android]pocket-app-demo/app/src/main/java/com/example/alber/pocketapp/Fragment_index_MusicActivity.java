package com.example.alber.pocketapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Fragment_index_MusicActivity extends AppCompatActivity {

    private int flag = 1;
    private File file;
    private String musicpath;
    private ImageView play,search,stop;
    private ImageView musicpic;
    private TextView musicName,musicartist;
    final MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_index__music);

        Permission.isGrantExternalRW(this,0);

        play = findViewById(R.id.music_play);
        search = findViewById(R.id.music_search);
        ImageView back = findViewById(R.id.btn_fragmentindexmusic_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                play.setImageResource(R.drawable.icon_play);
                Intent search = new Intent(Intent.ACTION_GET_CONTENT);
                search.setType("audio/*");
                search.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(search,1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        play = findViewById(R.id.music_play);
        stop = findViewById(R.id.music_stop);
        musicName = findViewById(R.id.music_name);
        musicartist = findViewById(R.id.music_artist);
        musicpic = findViewById(R.id.music_photo);

        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Audio.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
            int music = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
            cursor.moveToFirst();
            final String music_path = cursor.getString(music);
            file = new File(music_path);
        }

        musicpath = file.toString().trim();
        File song = new File(musicpath);
        if (song.exists()){
            try {
                mediaPlayer.setDataSource(musicpath);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(this,"文件不存在，请检查文件路径",Toast.LENGTH_SHORT).show();
        }

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(musicpath);
        try {
            String songname = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String songauther = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            musicName.setText(songname);
            if (songauther.equals("null"))
                musicartist.setText("未知歌手");
            else {
                musicartist.setText(songauther);
            }

            byte[] songpic = mmr.getEmbeddedPicture();
            Bitmap bitmap = BitmapFactory.decodeByteArray(songpic,0,songpic.length);
            musicpic.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.icon_pause);
                    flag++;
                }
                else {
                    if (!mediaPlayer.isPlaying()){
                        play.setImageResource(R.drawable.icon_pause);
                        goPlay();
                    }
                    else if (mediaPlayer.isPlaying()){
                        play.setImageResource(R.drawable.icon_play);
                        pause();
                    }
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null && mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            }
        });
    }

    public void goPlay(){
        int position = getCurrentProgress();
        mediaPlayer.seekTo(position);
        try{
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    public int getCurrentProgress(){
        if (mediaPlayer != null & mediaPlayer.isPlaying()){
            return mediaPlayer.getCurrentPosition();
        }
        else if (mediaPlayer != null & (!mediaPlayer.isPlaying())){
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void pause(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }


    //    int flag = 1;
//    private Button Start , Last , Stop , Next;
//    private TextView musicInfo;
//    private ListView musicList;
//    private SeekBar seekBar;
//    private MusicService musicService = new MusicService();
//    private Handler handler;
//    int update = 0x101;
//    private boolean autoChange , manulChange;
//    private boolean isPause;
//
//    private void setListViewAdapter(){
//        String[] str = new String[musicService.musicList.size()];
//        int i = 0;
//        for (Object path : musicService.musicList) {
//            File file = new File((String) path);
//            str[i++] = file.getName();
//        }
//        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,str);
//        musicList = findViewById(R.id.music_list);
//        musicList.setAdapter(adapter);
//    }
//
//    private String setPlayInfo (int position, int max){
//        String info = musicService.songName + "\n\n";
//        int pMinutes = 0;
//        while (position >= 60) {
//            pMinutes++;
//            position -= 60;
//        }
//        String now = (pMinutes < 10 ? "0" + pMinutes : pMinutes) + ":" + (position < 10 ? "0" + position : position);
//        int mMinutes = 0;
//        while (max >= 60) {
//            mMinutes++;
//            max -= 60;
//        }
//        String all = (mMinutes < 10 ? "0" + mMinutes : mMinutes) + ":" + (max < 10 ? "0" + max : max);
//
//        return info + now + " / " + all;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment_index__music);
//
//        try {
//            setListViewAdapter();
//        }catch (Exception e){
//            Log.d("TAG","读取信息失败！");
//        }
//
//        Start = findViewById(R.id.music_start);
//        Start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    if (flag == 1){
//                        musicService.Play();
//                        flag++;
//                    }
//                    else {
//                        if (!musicService.player.isPlaying()){
//                            musicService.goPlay();
//                        }
//                        else if (musicService.player.isPlaying()){
//                            musicService.pause();
//                        }
//                    }
//                }catch (Exception e){
//                    Log.d("TAG","开始异常！");
//                }
//            }
//        });
//
//        Stop = findViewById(R.id.music_stop);
//        Stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try{
//                    musicService.stop();
//                    flag = 1;
//                    seekBar.setProgress(0);
//                    musicInfo.setText("");
//                }catch (Exception e){
//                    Log.d("TAG","停止异常！");
//                }
//            }
//        });
//
//        Last = findViewById(R.id.music_last);
//        Last.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    musicService.last();
//                }catch (Exception e){
//                    Log.d("TAG","上一曲异常");
//                }
//            }
//        });
//
//        Next = findViewById(R.id.music_next);
//        Next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    musicService.next();
//                }catch (Exception e){
//                    Log.d("TAG","下一曲异常");
//                }
//            }
//        });
//
//        seekBar = findViewById(R.id.music_seekbar);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                int progress = seekBar.getProgress();
//                int musicMax = musicService.player.getDuration();
//                int seekBarMax = seekBar.getMax();
//                musicService.player.seekTo(musicMax * progress /seekBarMax);
//                autoChange = true;
//                manulChange = false;
//            }
//        });
//
//        Thread t = new Thread();
//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                int mMax = musicService.player.getDuration();
//                if (msg.what == update){
//                    try {
//                        seekBar.setProgress(msg.arg1);
//                        musicInfo.setText(setPlayInfo(msg.arg2 / 1000,mMax / 1000));
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                else {
//                    seekBar.setProgress(0);
//                    musicInfo.setText("播放已经停止");
//                }
//            }
//            public void run() {
//                int position, mMax, sMax;
//                while (!Thread.currentThread().isInterrupted()) {
//                    if (musicService.player != null && musicService.player.isPlaying()) {
//                        position = musicService.getCurrentProgress();//得到当前歌曲播放进度(秒)
//                        mMax = musicService.player.getDuration();//最大秒数
//                        sMax = seekBar.getMax();//seekBar最大值，算百分比
//                        Message m = handler.obtainMessage();//获取一个Message
//                        m.arg1 = position * sMax / mMax;//seekBar进度条的百分比
//                        m.arg2 = position;
//                        m.what = update;
//                        handler.sendMessage(m);
//                        //  handler.sendEmptyMessage(UPDATE);
//                        try {
//                            Thread.sleep(1000);// 每间隔1秒发送一次更新消息
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//
//        };
//        t.start();
//    }

}
