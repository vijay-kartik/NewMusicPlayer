package com.example.newmusicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;

import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> songs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mAppBar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mAppBar);
        getMusicFiles();
        //getMusicFilesFromPath();

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        adapter.addFragment(new ListFragment(songs), "List 1");
        adapter.addFragment(new RecyclerViewFragment(songs), "List2");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //setBtnClickListeners();

    }
    public void getMusicFilesFromPath() {
        songs = new ArrayList<String>();

        String filePath = Environment.getExternalStorageDirectory() + "/Card Data/Music";
        File[] files = new File(filePath).listFiles();
        for(File file1 : files) {
            if(file1.toString().endsWith(".mp3")){
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(file1.toString());
                String songName = file1.getName();
                String songAlbumName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                Log.d("kkk", "Track: " + songName + " | Album: " + songAlbumName);
                try {
                    byte[] picture = mmr.getEmbeddedPicture();
                    if (picture != null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                        ImageView img = findViewById(R.id.imageBmp);
                        img.setImageBitmap(bmp);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                mmr.release();
                mmr = null;
            }
            Log.d("kkk", file1 + "");
        }

    }

    public void getMusicFiles() {
        songs = new ArrayList<String>();

        //set the query parameters to get music files from the local data
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        String[] projection = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME};
        //initialise the cursor with query parameters
        Cursor listCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null);

        //iterate over query results to add music file name
        while(listCursor.moveToNext()){
            songs.add(listCursor.getString(0) + '|' + listCursor.getString(1).replace(".mp3"," "));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

//    public void setBtnClickListeners() {
//        final LinearLayout fragContainer = findViewById(R.id.listLayout);
//        listBtn = findViewById(R.id.listViewBtn);
//        recyclerBtn = findViewById(R.id.recyclerViewBtn);
//        clearBtn = findViewById(R.id.clearViewBtn);
//        listBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int frag_id = 2703;
//                if(fragContainer != null){
//                    fragContainer.removeAllViews();
//                }
//                LinearLayout fragLayout = new LinearLayout(MainActivity.this);
//                fragLayout.setId(frag_id);
//                fragLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//                getSupportFragmentManager().beginTransaction().add(fragLayout.getId(), new ListFragment(songs)).commit();
//                fragContainer.addView(fragLayout);
//            }
//        });
//
//        recyclerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int frag_id = 0403;
//                if(fragContainer != null){
//                    fragContainer.removeAllViews();
//                }
//                LinearLayout fragLayout = new LinearLayout(MainActivity.this);
//                fragLayout.setId(frag_id);
//                fragLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//                getSupportFragmentManager().beginTransaction().add(fragLayout.getId(), new RecyclerViewFragment(songs)).commit();
//                fragContainer.addView(fragLayout);
//            }
//        });
//    }




}
