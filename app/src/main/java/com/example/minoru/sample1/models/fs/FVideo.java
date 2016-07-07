package com.example.minoru.sample1.models.fs;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

/**
 * 動画ファイルモデルクラス
 */
public class FVideo {

    /**
     * 動画ファイルのエンティティクラス
     */
    public class Entity {

        /** 動がのビットレート */
        public int bitrate = -1;

        /** 動画の長さ */
        public long duration = -1;

        /** 動画のジャンル */
        public String genre = "";

        /** 動画のMIMEタイプ */
        public String mimetype = "";

        /** 動画のトラック数 */
        public int numTracks = -1;

        /** 動画のタイトル */
        public String title = "";

        /** 動画の画面高さ */
        public int videoHeight = -1;

        /** 動画の画面幅 */
        public int videoWidth = -1;

        /**
         * コンストラクタ
         *
         * @param mmr
         */
        public Entity(MediaMetadataRetriever mmr) {
            String bitrate = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
            String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            String genre = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
            String mimetype = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            String numTracks = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS);
            String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String videoHeight = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
            String videoWidth = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
            if (bitrate != null) { this.bitrate = Integer.parseInt(bitrate); }
            if (duration != null) { this.duration = Long.parseLong(duration); }
            if (genre != null) { this.genre = genre; }
            if (mimetype != null) { this.mimetype = mimetype; }
            if (numTracks != null) { this.numTracks = Integer.parseInt(numTracks); }
            if (title != null) { this.title = title; }
            if (videoHeight != null) { this.videoHeight = Integer.parseInt(videoHeight); }
            if (videoWidth != null) { this.videoWidth = Integer.parseInt(videoWidth); }
        }
    }

    /**
     * 動画ファイルのエンティティを生成する
     *
     * @return 生成したエンティティ
     */
    public Entity createEntity(String path) {
        Entity entity;
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            mmr.setDataSource(path);
            entity = new Entity(mmr);
        }
        catch (IllegalArgumentException e) {
            Log.e("createEntity error!", e.getMessage(), e);
            throw e;
        }
        return entity;
    }

    /**
     * 動画ファイルのエンティティを生成する
     *
     * @return 生成したエンティティ
     */
    public Entity createEntity(Context context, Uri uri) {
        Entity entity;
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            mmr.setDataSource(context, uri);
            entity = new Entity(mmr);
        }
        catch (IllegalArgumentException e) {
            Log.e("createEntity error!", e.getMessage(), e);
            throw e;
        }
        return entity;
    }
}
