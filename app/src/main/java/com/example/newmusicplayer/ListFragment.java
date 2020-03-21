package com.example.newmusicplayer;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.List;

public class ListFragment extends Fragment {
    private List<Data> songs;
    private ListView songListView;

    public ListFragment() {
        // Required empty public constructor
    }

    public ListFragment(List<Data> songList) {
        songs = songList;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        songListView = view.findViewById(R.id.musicFilesList);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, songs);
        songListView.setAdapter(adapter);

        return view;
    }

}