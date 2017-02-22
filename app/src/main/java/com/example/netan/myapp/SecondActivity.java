package com.example.netan.myapp;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;



public class SecondActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyBBJscHGbOXaaRwGAenXjkCGshUugHmPLk";
    public static final String VIDEO_ID = "kXNXH2oZRRs";
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et = (EditText) findViewById(R.id.editText2);
        TextView tv = (TextView) findViewById(R.id.textView3);
        SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
        et.setText(settings.getString("tvalue", ""));

        tv.setText(getIntent().getExtras().getString("thetext"));
        if (tv.getText().equals("")){
            tv.setText("You didn't put any Text");
        }

        /** Initializing YouTube Player VIew **/
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("tvalue", et.getText().toString());
        editor.commit();
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failured to initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        /** Add listeners to YoutubePlayer instance **/
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        /** Start buggering **/
        if (!wasRestored) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }

    }


    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean arg0) {

        }

        @Override
        public void onSeekTo(int arg0) {

        }
    };
   private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
       @Override
       public void onLoading() {

       }

       @Override
       public void onLoaded(String arg0) {

       }

       @Override
       public void onAdStarted() {

       }

       @Override
       public void onVideoStarted() {

       }

       @Override
       public void onVideoEnded() {

       }

       @Override
       public void onError(ErrorReason arg0) {

       }
   };
}
