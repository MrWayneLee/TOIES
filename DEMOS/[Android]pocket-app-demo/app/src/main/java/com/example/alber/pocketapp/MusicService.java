package com.example.alber.pocketapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MusicService {

    private static final File Path = Environment.getExternalStorageDirectory();
    public List musicList;
    public MediaPlayer player;
    public int songNum;
    public String songName;

    class MusicFilter implements FilenameFilter{
        @Override
        public boolean accept(File dir, String name) {
            return (name.endsWith(".flac"));
        }
    }

    public MusicService(){
        super();
        player = new MediaPlayer();
        musicList = new ArrayList();

        try{
            File MUSIC_PATH = new File(Path,"sdcard/qqmusic/song");
            if(MUSIC_PATH.listFiles(new MusicFilter()).length > 0){
                for (File file : MUSIC_PATH.listFiles(new MusicFilter())){
                    musicList.add(file.getAbsolutePath());
                }
            }
        }catch (Exception e){
            Log.d("TAG","读取文件异常");
        }
    }

    public void setPlayName(String dataSource){
        File file = new File(dataSource);
        String name = file.getName();
        int index = name.lastIndexOf(".");
        songName = name.substring(0,index);
    }

    public void Play(){
        try{
            player.reset();
            String dataSource = String.valueOf(musicList.get(songNum));
            setPlayName(dataSource);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(dataSource);
            player.prepare();
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer arg0) {
                    next();
                }
            });
        }catch (Exception e){
            Log.d("MusicService",e.getMessage());
        }
    }

    public void goPlay(){
        int position = getCurrentProgress();
        player.seekTo(position);
        try{
            player.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
        player.start();
    }

    public int getCurrentProgress(){
        if (player != null & player.isPlaying()){
            return player.getCurrentPosition();
        }
        else if (player != null & (!player.isPlaying())){
            return player.getCurrentPosition();
        }
        return 0;
    }

    public void next(){
        songNum = songNum == musicList.size() - 1 ? 0 : songNum + 1;
        Play();
    }

    public void last(){
        songNum = songNum == 0 ? musicList.size() - 1 : songNum - 1;
        Play();
    }

    public void pause(){
        if(player != null && player.isPlaying()){
            player.pause();
        }
    }

    public void stop(){
        if(player != null && player.isPlaying()){
            player.stop();
            player.reset();
        }
    }

}
