package com.ars.crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class ReadBiodata extends AppCompatActivity {
    protected Cursor cursor;
    SQLHelper dbHelper;
    Button ton2;
    TextView text1, text2, text3, text4, text5;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_biodata);
        dbHelper = new SQLHelper(this);

        text1 = findViewById(R.id.textView1);
        text2 = findViewById(R.id.textView2);
        text3 = findViewById(R.id.textView3);
        text4 = findViewById(R.id.textView4);
        text5 = findViewById(R.id.textView5);
        videoView = findViewById(R.id.videoView);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0));
            text2.setText(cursor.getString(1));
            text3.setText(cursor.getString(2));
            text4.setText(cursor.getString(3));
            text5.setText(cursor.getString(4));

            // Gantilah "crlm" dengan nama file video yang sesuai di folder res/raw/
            String videoPath = "android.resource://" + getPackageName() + "/raw/crlm";
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);

            // Tambahkan MediaController agar dapat mengontrol pemutaran video
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            // Jalankan video
            videoView.start();
        }

        ton2 = findViewById(R.id.button1);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
