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
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            LayoutInflater infleater = (LayoutInflater) getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            View row = infleater.inflate(R.layout.list_item, parent, false);
            String[] items = getResources().getStringArray(R.array.countries);
            ImageView iv = (ImageView) row.findViewById(R.id.imageView);
            TextView tv = (TextView) row.findViewById(R.id.textView);
            tv.setText(items[position]);

            switch (items[position]) {
                case "United States":
                    iv.setImageResource(R.drawable.usa);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer mp_usa = MediaPlayer.create(ListActivity.this, R.raw.usa);
                            mp_usa.start();
                        }
                    });
                    break;
                case "Brazil":
                    iv.setImageResource(R.drawable.brazil);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer mp_usa = MediaPlayer.create(ListActivity.this, R.raw.brazil);
                            mp_usa.start();
                        }
                    });
                    break;
                case "France":
                    iv.setImageResource(R.drawable.france);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer mp_usa = MediaPlayer.create(ListActivity.this, R.raw.paris);
                            mp_usa.start();
                        }
                    });
                    break;
                case "Japan":
                    iv.setImageResource(R.drawable.japan);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer mp_usa = MediaPlayer.create(ListActivity.this, R.raw.japan);
                            mp_usa.start();
                        }
                    });
                    break;
                case "Russia":
                    iv.setImageResource(R.drawable.russia);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer mp_usa = MediaPlayer.create(ListActivity.this, R.raw.russia);
                            mp_usa.start();
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
