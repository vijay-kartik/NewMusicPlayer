package com.example.newmusicplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class RecyclerViewFragment extends Fragment {

    private RecyclerView fRecyclerView;
    private ArrayList<String> songs;
    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    public RecyclerViewFragment(ArrayList<String> songList) {
        songs = songList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        fRecyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        fRecyclerView.setLayoutManager(manager);

        MusicAdapter adapter = new MusicAdapter(songs);
        fRecyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}
