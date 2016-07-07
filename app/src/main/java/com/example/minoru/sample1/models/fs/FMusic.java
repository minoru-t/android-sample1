package com.example.minoru.sample1.models.fs;

import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * 音楽ファイルモデルクラス
 *
 * <b>Necessary permissions</b>
 *
 * <ul>
 *     <li>android.permission.READ_EXTERNAL_STORAGE</li>
 * </ul>
 */
public class FMusic {

    /**
     * 音楽ファイルのエンティティクラス
     */
    public class Entity {

        //-------------------------------------------------------
        // private, protected fields
        //-------------------------------------------------------

        /** アルバム名 */
        private String album = "";

        /** アルバムアーティスト名 */
        private String albumartist = "";

        /** アーティスト名 */
        private String artist = "";

        /** ビットレート */
        private int bitrate = -1;

        /** CD内のトラックナンバー */
        private int cdTrackNumber = -1;

        /** 長さ（ミリ秒） */
        private long duration = -1;

        /** ジャンル */
        private String genre = "";

        /** MIMEタイプ */
        private String mimetype = "";

        /** 曲名 */
        private String title = "";

        /** ファイルのフルパス */
        private String fullPath = "";

        //-------------------------------------------------------
        // public fields
        //-------------------------------------------------------

        /** アルバム名 */
        public String getAlbum() {
            return album;
        }

        /** アルバムアーティスト名 */
        public String getAlbumartist() {
            return albumartist;
        }

        /** アーティスト名 */
        public String getArtist() {
            return artist;
        }

        /** ビットレート */
        public int getBitrate() {
            return bitrate;
        }

        /** CD内のトラックナンバー */
        public int getCdTrackNumber() {
            return cdTrackNumber;
        }

        /** 長さ（ミリ秒） */
        public long getDuration() {
            return duration;
        }

        /** ジャンル */
        public String getGenre() {
            return genre;
        }

        /** MIMEタイプ */
        public String getMimetype() {
            return mimetype;
        }

        /** 曲名 */
        public String getTitle() {
            return title;
        }

        /** ファイルのフルパス */
        public String getFullPath() {
            return fullPath;
        }

        //-------------------------------------------------------
        // public methods
        //-------------------------------------------------------

        /**
         * コンストラクタ
         *
         * @param fullPath ファイルのフルパス
         */
        public Entity(String fullPath) throws IllegalArgumentException {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(fullPath);
            String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String albumartist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);
            String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String bitrate = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
            String cdTrackNumber = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER);
            String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            String genre = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
            String mimetype = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            if (album != null) { this.album = album; }
            if (albumartist != null) { this.albumartist = albumartist; }
            if (artist != null) { this.artist = artist; }
            if (bitrate != null) { this.bitrate = Integer.parseInt(bitrate); }
            if (cdTrackNumber != null) { this.cdTrackNumber = Integer.parseInt(cdTrackNumber); }
            if (duration != null) { this.duration = Long.parseLong(duration); }
            if (genre != null) { this.genre = genre; }
            if (mimetype != null) { this.mimetype = mimetype; }
            if (title != null) { this.title = title; }
            this.fullPath = fullPath;
        }

        //-------------------------------------------------------
        // private, protected methods
        //-------------------------------------------------------

    }

    //-------------------------------------------------------
    // public fields
    //-------------------------------------------------------

    /** 保存先フォルダパス（スラッシュ終わり） */
    public static final String SAVE_PATH = Environment.getExternalStorageDirectory().getPath() + "/Music/";

    //-------------------------------------------------------
    // private, protected fields
    //-------------------------------------------------------

    /** 唯一のインスタンス */
    private static FMusic instance = new FMusic();

    /** 曲別のリスト */
    ArrayList<Entity> allMusic = new ArrayList<Entity>();

    /** ジャンル一覧 */
    ArrayList<String> genres = new ArrayList<String>();

    /** ジャンル別のリスト */
    ArrayList<ArrayList<Entity>> musicByGenre = new ArrayList<ArrayList<Entity>>();

    /** アーティスト一覧 */
    ArrayList<String> artists = new ArrayList<String>();

    /** アーティスト別のリスト */
    ArrayList<ArrayList<Entity>> musicByArtist = new ArrayList<ArrayList<Entity>>();

    /** アルバム一覧 */
    ArrayList<String> albums = new ArrayList<String>();

    /** アルバム別のリスト */
    ArrayList<ArrayList<Entity>> musicByAlbum = new ArrayList<ArrayList<Entity>>();

    /** プレイリスト一覧 */

    /** プレイリスト別のリスト */

    //-------------------------------------------------------
    // public methods
    //-------------------------------------------------------

    /**
     * インスタンスを取得する
     *
     * @return FMusicクラスのインスタンス
     */
    public static FMusic getInstance() {
        return instance;
    }

    /**
     * 音楽ファイルを読み込む
     */
    public void load() {

        // リストを初期化
        this.allMusic.clear();
        this.genres.clear();
        this.artists.clear();
        this.albums.clear();
        this.musicByGenre.clear();
        this.musicByArtist.clear();
        this.musicByAlbum.clear();

        // 保存先のファイルを読み込み
        this.loadFiles(this.SAVE_PATH);

        // TODO: リストの並び替え処理
    }

    //-------------------------------------------------------
    // private, protected methods
    //-------------------------------------------------------

    /**
     * コンストラクタ
     */
    private FMusic() {
        ;
    }

    /**
     * フォルダ内の音楽ファイルを読み込む
     *
     * @param path
     */
    private void loadFiles(String path) {

        // 保存先のファイルをすべて取得
        File[] files = new File(path).listFiles();

        // すべてのファイルに対して処理
        for (File file : files) {
            // ディレクトリの場合
            if (file.isDirectory()) {
                // 再帰で処理
                this.loadFiles(file.getPath());
            }
            // ファイルの場合
            else if (file.isFile()) {

                try {
                    // エンティティを生成して、各グループに割り振る
                    Entity entity = new Entity(file.getPath());
                    // TODO: 対象外の拡張子の場合はスキップする処理作成
                    this.allMusic.add(entity);
                    this.addMusicByGenre(entity);
                    this.addMusicByArtist(entity);
                    this.addMusicByAlbum(entity);
                    // TODO: プレイリスト別のリスト作成
                }
                catch (IllegalArgumentException e) {
                    Log.e("grouping genre error!", e.getMessage());
                    // 処理を継続する
                }
            }
            // その他の場合
            else {
                ;
            }
        }
    }

    /**
     * ジャンル別に音楽を追加する
     *
     * @param entity
     */
    private void addMusicByGenre(Entity entity) {

        // 対象ジャンルの要素位置を取得する
        int index = this.genres.indexOf(entity.getGenre());

        // 存在するジャンルの場合、対象の要素にエンティティを追加する
        if (index != -1) {
            this.musicByGenre.get(index).add(entity);
        }
        // 存在しないジャンルの場合、ジャンルを追加して、追加したジャンルの要素にエンティティを追加する
        else {
            int beforeSize = this.genres.size();
            this.genres.add(entity.getGenre());
            this.musicByGenre.add(new ArrayList<Entity>());
            this.musicByGenre.get(beforeSize).add(entity);
        }
    }

    /**
     * アーティスト別に音楽を追加する
     *
     * @param entity
     */
    private void addMusicByArtist(Entity entity) {

        // 対象アーティストの要素位置を取得する
        int index = this.artists.indexOf(entity.getArtist());

        // 存在するアーティストの場合、対象の要素にエンティティを追加する
        if (index != -1) {
            this.musicByArtist.get(index).add(entity);
        }
        // 存在しないアーティストの場合、アーティストを追加して、追加したアーティストの要素にエンティティを追加する
        else {
            int beforeSize = this.artists.size();
            this.artists.add(entity.getArtist());
            this.musicByArtist.add(new ArrayList<Entity>());
            this.musicByArtist.get(beforeSize).add(entity);
        }
    }

    /**
     * アルバム別に音楽を追加する
     *
     * @param entity
     */
    private void addMusicByAlbum(Entity entity) {

        // 対象アルバムの要素位置を取得する
        int index = this.albums.indexOf(entity.getAlbum());

        // 存在するアルバムの場合、対象の要素にエンティティを追加する
        if (index != -1) {
            this.musicByAlbum.get(index).add(entity);
        }
        // 存在しないアルバムの場合、アルバムを追加して、追加したアルバムの要素にエンティティを追加する
        else {
            int beforeSize = this.albums.size();
            this.albums.add(entity.getAlbum());
            this.musicByAlbum.add(new ArrayList<Entity>());
            this.musicByAlbum.get(beforeSize).add(entity);
        }
    }
}
