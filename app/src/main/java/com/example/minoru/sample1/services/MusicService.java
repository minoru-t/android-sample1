package com.example.minoru.sample1.services;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Minoru on 2016/06/26.
 */
public class MusicService extends MediaPlayer {

    /** 唯一のインスタンス */
    private static MusicService instance = new MusicService();

    /** 再生している音楽のパス */
    private String musicPath;

    /** 再生している音楽の情報 */
    private MediaMetadataRetriever currentInfo = new MediaMetadataRetriever();

    private static Context currentContext;
    private Uri currentUri;

    /**
     * インスタンスを取得する
     *
     * @return 唯一のインスタンス
     */
    public static MusicService getInstance(Context context) {
        currentContext = context;
        return instance;
    }

    public void setDataSource(Uri uri) {
        try {
            currentUri = uri;
            this.setDataSource(currentContext, uri);
            this.prepare();

            currentInfo.setDataSource(currentContext, uri);
            String s1 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String s2 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);
            String s3 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String s4 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
            String s5 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
            String s6 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CAPTURE_FRAMERATE);
            String s7 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER);
            String s8 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPILATION);
            String s9 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPOSER);
            String s10 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE);
            String s11 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER);
            String s12 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            String s13 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
            String s14 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_AUDIO);
            String s15 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_VIDEO);
            String s16 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_LOCATION);
            String s17 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            String s18 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS);
            String s19 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String s20 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
            String s21 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
            String s22 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
            String s23 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_WRITER);
            String s24 = currentInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR);
            if (s1 != null) Log.d("s1: ", s1);
            if (s2 != null) Log.d("s2: ", s2);
            if (s3 != null) Log.d("s3: ", s3);
            if (s4 != null) Log.d("s4: ", s4);
            if (s5 != null) Log.d("s5: ", s5);
            if (s6 != null) Log.d("s6: ", s6);
            if (s7 != null) Log.d("s7: ", s7);
            if (s8 != null) Log.d("s8: ", s8);
            if (s9 != null) Log.d("s9: ", s9);
            if (s10 != null) Log.d("s10: ", s10);
            if (s11 != null) Log.d("s11: ", s11);
            if (s12 != null) Log.d("s12: ", s12);
            if (s13 != null) Log.d("s13: ", s13);
            if (s14 != null) Log.d("s14: ", s14);
            if (s15 != null) Log.d("s15: ", s15);
            if (s16 != null) Log.d("s16: ", s16);
            if (s17 != null) Log.d("s17: ", s17);
            if (s18 != null) Log.d("s18: ", s18);
            if (s19 != null) Log.d("s19: ", s19);
            if (s20 != null) Log.d("s20: ", s20);
            if (s21 != null) Log.d("s21: ", s21);
            if (s22 != null) Log.d("s22: ", s22);
            if (s23 != null) Log.d("s23: ", s23);
            if (s24 != null) Log.d("s24: ", s24);
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Uri uri) {
        this.setDataSource(uri);
        this.start();
    }

    public void start() {
        if (this.isPlaying()) {
            this.stop();
        }
        try {
            this.start();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void start(String path) {
        if (this.isPlaying()) {
            this.stop();
        }
        try {
            this.setDataSource(path);
            this.prepare();
            this.start();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 再生と一時停止をする
     */
    public void startAndPause() {
        try {
            if (this.isPlaying()) {
                this.pause();
            }
            else {
                this.start();
            }
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public boolean previous() {
        return true;
    }

    public boolean next() {
        return true;
    }

    /**
     * コンストラクタ
     */
    private MusicService() {
        super();
    }
}
