package com.example.netan.myapp;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {


    // Context menu that gonna contain the menu with the radio button
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflat = getMenuInflater();
        inflat.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Context Menu");

        //Use the preference menu
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean first = settings.getBoolean("first", false); //key and default value
        boolean second = settings.getBoolean("second", false); //key and default value

        if (first){
            Toast toast = Toast.makeText(MainActivity.this, "First options was checked and saved", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
        if (second){
            Toast toast = Toast.makeText(MainActivity.this, "Second options was checked and saved", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }

    }
    //Context Menue - Taping on each item, leads to new activity
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            startActivity(new Intent(MainActivity.this, ThirdActiviry.class));
        }
        if (item.getItemId() == R.id.item2) {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));
        }
        if (item.getItemId() == R.id.item3) { //send the user to the preference menu
            Intent intent = new Intent(MainActivity.this, Preferences.class);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check i if the wifi is on and put it in the text vew
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
        pd.setMessage("Now i'm protending to think... Tap back to stop it.");
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are You sure you want to quit?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes,", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        // Notification
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        PendingIntent pending = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher); //Large icon
                        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify = new Notification.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.stat_napp_icon)
                                //set large notification
                                .setLargeIcon(bm)
                                //Set notification text
                                .setTicker("Knock, Knock, Knock")
                                .setContentTitle("You Have Closed the app")
                                .setContentText("And now you got a notification!")
                                .setContentIntent(pending)
                                .setWhen(System.currentTimeMillis())
                                .setAutoCancel(true)
                                .build();
                        notify.sound = Uri.parse("android.resource://com.example.netan.myapp/" + R.raw.beep);
                        nm.notify(0, notify);


                        // Exit the app
                        MainActivity.this.finish();
                    }
                });
                builder.setNegativeButton("No!", new DialogInterface.OnClickListener() {
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
                d.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); // Makes the dialog transparent
                d.show();
                Toast toast = Toast.makeText(MainActivity.this, "This is My Dialog", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
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

            //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST);
            }
        });

    }
    static final int CAMERA_REQUEST = 1888;
    //Connecting the image view to the image that was caputred on cam
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            iv2.setImageBitmap(bm);
        }
       // }
        //super.onActivityResult(requestCode, resultCode, data);


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
