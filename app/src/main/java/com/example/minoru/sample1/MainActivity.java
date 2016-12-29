package com.example.minoru.sample1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.example.minoru.sample1.models.fs.FMusic;
import com.example.minoru.sample1.services.MusicService;
import com.example.minoru.sample1.services.VideoService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MusicService musicService;
    VideoService videoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        musicService = MusicService.getInstance(this);
        videoService = VideoService.getInstance(this);
        Uri musicUri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.music_13);
        musicService.setDataSource(musicUri);
        final OnClicks onclicks = new OnClicks();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setContentView(R.layout.activity_main);
        findViewById(R.id.clickme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclicks.clickme(view);
            }
        });
        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclicks.btnPlay(view);
            }
        });
        // 動画を再生する
        // thanks sample files.
        // @see http://www.sample-videos.com/
        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        // URL指定の場合※「INTERNET」パーミッションが必要
//        videoView.setVideoURI(Uri.parse("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_5mb.mp4"));
        Uri videoURI = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.samplevideo_720x480_2mb);
        videoView.setVideoURI(videoURI);
        // 再生開始
        videoView.start();
        videoService.setVideoURI(videoURI);

        Tokenizer tokenizer = new Tokenizer() ;
        List<Token> tokens = tokenizer.tokenize("お寿司が食べたい。");
        for (Token token : tokens) {
            Log.d(getString(R.string.app_name), token.getSurface() + "\t" + token.getAllFeatures());
        }

        FMusic m = FMusic.getInstance();
        m.load();
        ArrayList genres = m.getGenres();
        ArrayList artists = m.getArtists();
        ArrayList albums = m.getAlbums();
        Log.d("test", "loaded2");

    }

    private class OnClicks {
        /**
         *
         * @param view
         */
        public void clickme(View view) {
            Toast.makeText(view.getContext(), "OnClicks.clickme!", Toast.LENGTH_LONG).show();
            Snackbar.make(view, "OnClicks.clickme!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
//            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//            EditText editText = (EditText) findViewById(R.id.edittext);
//            intent.putExtra("inputText", editText.getText().toString());
            Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
            startActivity(intent);
        }
        public void btnPlay(View view) {
            Toast.makeText(view.getContext(), "Start MusicService!", Toast.LENGTH_LONG).show();
            musicService.startAndPause();
            Button btn = (Button)findViewById(R.id.btnPlay);
            if (musicService.isPlaying()) {
                btn.setText(R.string.btnStop);
            }
            else {
                btn.setText(R.string.btnPlay);
            }
        }
    }

    private void onClickClickme(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.edittext);
        intent.putExtra("inputText", editText.getText().toString());
        startActivity(intent);
        Toast.makeText(view.getContext(), "onClickClickme", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
