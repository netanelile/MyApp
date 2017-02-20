package com.example.netan.myapp;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ListActivity extends android.app.ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setListAdapter( new MyAdapter(this, android.R.layout.simple_list_item_1, R.id.textView,
                getResources().getStringArray(R.array.countries)));

    }



    private class MyAdapter extends ArrayAdapter<String> {

        MyAdapter(Context context, int resource, int textViewResourceId, String[] strings) {
            super(context, resource, textViewResourceId, strings);
        }

        @NonNull
        @Override
        public View getView(int position, final View convertView, @NonNull ViewGroup parent) {

            LayoutInflater infleater = (LayoutInflater) getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            final View row = infleater.inflate(R.layout.list_item, parent, false);
            String[] items = getResources().getStringArray(R.array.countries);
            ImageView iv = (ImageView) row.findViewById(R.id.imageView);
            final TextView tv = (TextView) row.findViewById(R.id.textView);
            tv.setText(items[position]);
            //Mecia files
            final MediaPlayer mp_usa = MediaPlayer.create(ListActivity.this, R.raw.usa);
            final MediaPlayer mp_brazil = MediaPlayer.create(ListActivity.this, R.raw.brazil);
            final MediaPlayer mp_paris = MediaPlayer.create(ListActivity.this, R.raw.paris);
            final MediaPlayer mp_japan = MediaPlayer.create(ListActivity.this, R.raw.japan);
            final MediaPlayer mp_russia = MediaPlayer.create(ListActivity.this, R.raw.russia);
            
            switch (items[position]) {
                case "United States":
                    iv.setImageResource(R.drawable.usa);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(mp_usa.isPlaying()) {
                                mp_usa.pause();
                                int length = mp_usa.getCurrentPosition();
                                mp_usa.seekTo(length);
                            } else {
                                mp_usa.start();

                            }
                        }
                    });
                    break;
                case "Brazil":
                    iv.setImageResource(R.drawable.brazil);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mp_brazil.isPlaying()) {
                                mp_brazil.pause();
                                int length = mp_brazil.getCurrentPosition();
                                mp_brazil.seekTo(length);
                            } else {
                                mp_brazil.start();
                            }
                        }
                    });
                    break;
                case "France":
                    iv.setImageResource(R.drawable.france);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (mp_paris.isPlaying()) {
                                mp_paris.pause();
                                int length = mp_paris.getCurrentPosition();
                                mp_paris.seekTo(length);
                            } else {
                                mp_paris.start();
                            }
                        }
                    });
                    break;
                case "Japan":
                    iv.setImageResource(R.drawable.japan);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (mp_japan.isPlaying()) {
                                mp_japan.pause();
                                int length = mp_japan.getCurrentPosition();
                                mp_japan.seekTo(length);
                            } else {
                                mp_japan.start();
                            }
                        }
                    });
                    break;
                case "Russia":
                    iv.setImageResource(R.drawable.russia);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (mp_russia.isPlaying()) {
                                mp_russia.pause();
                                int length = mp_russia.getCurrentPosition();
                                mp_russia.seekTo(length);
                            } else {
                                mp_russia.start();
                            }
                        }
                    });
                    break;
            }
            return row;
        }

    }

    private void onListItemClick() {
    }
}
