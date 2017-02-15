package com.example.netan.myapp;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.provider.MediaStore;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // Cotext menu that gonna contain the menu with the radio button
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflat = getMenuInflater();
        inflat.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Context Menu");

    }
    ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CHeck i if the wifi is on and put it in the text vew
        ConnectivityManager conman = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        TextView tv = (TextView) findViewById(R.id.textView);
        NetworkInfo wifi = conman.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

       // boolean wifi = conman.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        if (wifi.isConnectedOrConnecting()) {
            tv.setText("WIFI is Connected");
        }
        else {
            tv.setText("WIFI is NOT connected");
        }

        // Progress Dialog
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("תפני ימינה, ואז שמאלה, ושם את תמותי!");
        pd.setIndeterminate(true);
        pd.setCancelable(true);

        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
            }
        });

        // Context menu with 2 Radio buttons
        Button b = (Button) findViewById(R.id.button);
        registerForContextMenu(b);

        // Dialog with Yes and no options. Yes will close the app. && Raising a notification
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are You sure Stupid?!");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes,", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {


                        // NOtification
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        PendingIntent pending = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify = new Notification.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.stat_napp_icon)
                                .setTicker("Knock, Knock, Knock")
                                .setContentTitle("I am the angel of Death")
                                .setContentText("Preapare to Die!")
                                .setWhen(System.currentTimeMillis())
                                .setContentIntent(pending)
                                .build();
                        nm.notify(0, notify);
                        MediaPlayer mp_beep = MediaPlayer.create(MainActivity.this, R.raw.beep);
                        mp_beep.start();

                        // Exit the app
                        MainActivity.this.finish();
                    }
                });
                builder.setNegativeButton("Hell No!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.create();
                alert.show();

            }
        });
        // Entering text, pushing a button and the text is display in a text view at the second activity

        final EditText et = (EditText) findViewById(R.id.editText);
        Button b6 = (Button) findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("thetext", et.getText().toString());
            startActivity(intent);
            }
        });

        // costumed dialog menue && Also rasing a toast
        Button b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d = new Dialog(MainActivity.this);
                d.setContentView(R.layout.dialog);
                d.setTitle("This Is MY Dialog");
                d.show();

                Toast toast = Toast.makeText(MainActivity.this, "טוסטר משולשים!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        });
        Button b5 = (Button) findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });

        // image view that display image that was capture from the camera
        iv2 = (ImageView) findViewById(R.id.imageView2); //using the global variable to ithe image view form the XML

        iv2.setOnClickListener(new View.OnClickListener() { // Creating on CLick listener for the image view
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

    }


    //Connecting the image view to the image that was caputred on cam
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bm = (Bitmap) data.getExtras().get("data");
        iv2.setImageBitmap(bm);
    }

    //Main Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    //Menu options: 1. Go to activity with animation. 2. Go to activity with list
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            startActivity(new Intent(MainActivity.this, AnimationActivity.class));
        }
        if (item.getItemId() == R.id.item2) {
            startActivity(new Intent(MainActivity.this, ListActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //Button with image refer to video activity




}
