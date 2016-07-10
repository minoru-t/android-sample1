package com.example.minoru.sample1.models.fs;

import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

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
        // getter methods
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

        /**
         * タイトルを返す
         *
         * @return
         */
        public String toString() {
            return this.getTitle();
        }

        //-------------------------------------------------------
        // private, protected methods
        //-------------------------------------------------------

    }

    /**
     * タイトルの並び替えクラス
     * 全角、半角を無視して並び替える
     */
    private class TitleComparator implements Comparator<Entity> {

        @Override
        public int compare(Entity lhs, Entity rhs) {
            String s1 = Normalizer.normalize(lhs.getTitle(), Normalizer.Form.NFKC);
            String s2 = Normalizer.normalize(rhs.getTitle(), Normalizer.Form.NFKC);
            return s1.compareTo(s2);
        }
    }

    /**
     * CDトラック番号の並び替えクラス
     */
    private class CdTrackNumberComparator implements Comparator<Entity> {

        @Override
        public int compare(Entity lhs, Entity rhs) {
            return lhs.getCdTrackNumber() - rhs.getCdTrackNumber();
        }
    }

    /**
     * 文字列並び替えクラス
     * 全角、半角を無視して並び替える
     */
    private class StringComparator implements Comparator<String> {

        @Override
        public int compare(String lhs, String rhs) {
            String s1 = Normalizer.normalize(lhs, Normalizer.Form.NFKC);
            String s2 = Normalizer.normalize(rhs, Normalizer.Form.NFKC);
            return s1.compareTo(s2);
        }
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
    private ArrayList<Entity> allMusic = new ArrayList<Entity>();

    /** ジャンル一覧 */
    private HashMap<String, Integer> genres = new HashMap<String, Integer>();

    /** ジャンル別のリスト */
    private ArrayList<ArrayList<Entity>> musicByGenre = new ArrayList<ArrayList<Entity>>();

    /** アーティスト一覧 */
    private HashMap<String, Integer> artists = new HashMap<String, Integer>();

    /** アーティスト別のリスト */
    private ArrayList<ArrayList<Entity>> musicByArtist = new ArrayList<ArrayList<Entity>>();

    /** アルバム一覧 */
    private HashMap<String, Integer> albums = new HashMap<String, Integer>();

    /** アルバム別のリスト */
    private ArrayList<ArrayList<Entity>> musicByAlbum = new ArrayList<ArrayList<Entity>>();

    //-------------------------------------------------------
    // getter methods
    //-------------------------------------------------------

    /** 曲別のリスト */
    public ArrayList<Entity> getAllMusic() {
        return allMusic;
    }

    /**
     * ジャンル一覧を取得する
     * ※ジャンル名で並び替え済みのリスト
     *
     * @return
     */
    public ArrayList<String> getGenres() {
        ArrayList<String> al = new ArrayList<String>(this.genres.keySet());
        Collections.sort(al, new StringComparator());
        return al;
    }

    /** ジャンル別のリスト */
    public ArrayList<ArrayList<Entity>> getMusicByGenre() {
        return musicByGenre;
    }

    /**
     * アーティスト一覧を取得する
     * ※アーティスト名で並び替え済みのリスト
     *
     * @return
     */
    public ArrayList<String> getArtists() {
        ArrayList<String> al = new ArrayList<String>(this.artists.keySet());
        Collections.sort(al, new StringComparator());
        return al;
    }

    /** アーティスト別のリスト */
    public ArrayList<ArrayList<Entity>> getMusicByArtist() {
        return musicByArtist;
    }

    /**
     * アルバム一覧を取得する
     * ※アルバム名で並び替え済みのリスト
     *
     * @return
     */
    public ArrayList<String> getAlbums() {
        ArrayList<String> al = new ArrayList<String>(this.albums.keySet());
        Collections.sort(al, new StringComparator());
        return al;
    }

    /** アルバム別のリスト */
    public ArrayList<ArrayList<Entity>> getMusicByAlbum() {
        return musicByAlbum;
    }

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

        // すべての音楽の並び替え
        Collections.sort(this.allMusic, new TitleComparator());

        // ジャンル別音楽の並び替え
        for (ArrayList<Entity> genres : this.musicByGenre) {
            Collections.sort(genres, new TitleComparator());
        }

        // アーティスト別音楽の並び替え
        for (ArrayList<Entity> artists : this.musicByArtist) {
            Collections.sort(artists, new TitleComparator());
        }

        // アルバム別トラック番号での並び替え
        for (ArrayList<Entity> albums : this.musicByAlbum) {
            Collections.sort(albums, new CdTrackNumberComparator());
        }
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
        String genre = entity.getGenre();
        boolean contains = this.genres.containsKey(entity.getGenre());

        // 存在するジャンルの場合、対象の要素にエンティティを追加する
        if (contains) {
            this.musicByGenre.get(this.genres.get(genre)).add(entity);
        }
        // 存在しないジャンルの場合、ジャンルを追加して、追加したジャンルの要素にエンティティを追加する
        else {
            int beforeSize = this.genres.size();
            this.genres.put(genre, beforeSize);
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
        String artist = entity.getArtist();
        boolean contains = this.artists.containsKey(artist);

        // 存在するアーティストの場合、対象の要素にエンティティを追加する
        if (contains) {
            this.musicByArtist.get(this.artists.get(artist)).add(entity);
        }
        // 存在しないアーティストの場合、アーティストを追加して、追加したアーティストの要素にエンティティを追加する
        else {
            int beforeSize = this.artists.size();
            this.artists.put(artist, beforeSize);
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
        String album = entity.getAlbum();
        boolean contains = this.albums.containsKey(album);

        // 存在するアルバムの場合、対象の要素にエンティティを追加する
        if (contains) {
            this.musicByAlbum.get(this.albums.get(album)).add(entity);
        }
        // 存在しないアルバムの場合、アルバムを追加して、追加したアルバムの要素にエンティティを追加する
        else {
            int beforeSize = this.albums.size();
            this.albums.put(album, beforeSize);
            this.musicByAlbum.add(new ArrayList<Entity>());
            this.musicByAlbum.get(beforeSize).add(entity);
        }
    }
}
