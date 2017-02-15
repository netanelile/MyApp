package com.example.netan.myapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView v = (VideoView) findViewById(R.id.videoView);
        String uriPath = "android.resource://" + getPackageName() + "/" +R.raw.vid;
        Uri uri = Uri.parse(uriPath);
        v.setVideoURI(uri);
        v.setMediaController(new MediaController(this));
        v.start();
        v.requestFocus();
    }
}
