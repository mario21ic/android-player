package com.mario21ic.android_player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements InterfaceMyPlay {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter adaptador;
    private Vector<String> misdatos;

    MediaPlayer mp;
    Button btnplay;
    Integer songPosition;
    Integer resIdSound;
//    Vector<String>() misdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnplay = findViewById(R.id.btnplay);
        recyclerView = findViewById(R.id.recycler_view);

        mp = MediaPlayer.create(this, R.raw.music0);
//        mp.start();

        misdatos = new Vector<String>();
        misdatos.add("Everyone is so alive");
        misdatos.add("Demo music file");
        misdatos.add("High Technologic Beat");
        misdatos.add("You know why");

        adaptador = new MyAdapter(this,
                misdatos);
        recyclerView.setAdapter(adaptador);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
        btnplay.setBackgroundResource(android.R.drawable.ic_media_pause);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
        btnplay.setBackgroundResource(android.R.drawable.ic_media_play);
    }


    @Override
    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void playSong(int position) {
//        Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        songPosition = position;
        resIdSound = getResources().getIdentifier(
                "music" + String.valueOf(songPosition), "raw", this.getPackageName());
        if (mp != null) {
//        mp = MediaPlayer.create(this, resIdSound);
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, resIdSound);
            }
            mp.start();
            btnplay.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void btnPlay(View view) {
//        Toast.makeText(this, "songPosition: " + String.valueOf(songPosition), Toast.LENGTH_SHORT).show();
        if (mp.isPlaying()) {
            mp.pause();
            btnplay.setBackgroundResource(android.R.drawable.ic_media_play);
        } else {
            mp.start();
            btnplay.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void btnPause(View v) {
        mp.pause();
        btnplay.setBackgroundResource(android.R.drawable.ic_media_play);
    }

    // TODO: add circle linkedlists and some unit tests
    public void btnNext(View view) {
//        Toast.makeText(this, "Playing position: "+ i + " - song:  " + String.valueOf(songPosition), Toast.LENGTH_SHORT).show();
        if (songPosition < 3) {
            songPosition = songPosition + 1;
        }
        resIdSound = getResources().getIdentifier(
                "music" + String.valueOf(songPosition), "raw", this.getPackageName());
        if (mp != null) {
//            mp = MediaPlayer.create(this, resIdSound);
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, resIdSound);
            }
            mp.start();
            btnplay.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void btnPrev(View view) {
//        Toast.makeText(this, "Playing position: "+ i + " - song:  " + String.valueOf(songPosition), Toast.LENGTH_SHORT).show();
        if (songPosition > 0) {
            songPosition = songPosition - 1;
        }
        resIdSound = getResources().getIdentifier(
                "music" + String.valueOf(songPosition), "raw", this.getPackageName());
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, resIdSound);
            }
            mp.start();
            btnplay.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }
}