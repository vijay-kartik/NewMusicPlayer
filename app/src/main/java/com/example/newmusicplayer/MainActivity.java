package com.example.newmusicplayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Data> songs = new ArrayList<Data>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mAppBar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mAppBar);
        getDataList();

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        //hello hello hello hello
        adapter.addFragment(new ListFragment(songs), "List 1");
        adapter.addFragment(new RecyclerViewFragment(songs), "List2");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }

    public void getDataList() {
        String filePath = Environment.getExternalStorageDirectory() + "/Card Data/Music";
        File[] files = new File(filePath).listFiles();
        for(File file1 : files) {
            if(file1.toString().endsWith(".mp3")){
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(file1.toString());
                String songName = file1.getName();
                String songAlbumName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                String songGenre = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);

                try {
                    byte[] picture = mmr.getEmbeddedPicture();
                    if (picture != null && songGenre != null && songAlbumName != null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                        Data data = new Data(songName, bmp, songGenre.concat(" | ").concat(songAlbumName));
                        songs.add(data);
                        mmr.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mmr = null;
            }
        }
    }

//    public void getMusicFiles() {
//        songs = new ArrayList<Data>();
//
//        //set the query parameters to get music files from the local data
//        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
//        String[] projection = {MediaStore.Audio.Media._ID,
//                MediaStore.Audio.Media.DISPLAY_NAME};
//        //initialise the cursor with query parameters
//        Cursor listCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null);
//
//        while(listCursor.moveToNext()){
//            songs.add(listCursor.getString(0) + '|' + listCursor.getString(1).replace(".mp3"," "));
//        }
//    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
