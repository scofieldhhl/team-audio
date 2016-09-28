/*
 * Copyright (C) 2015 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.naman14.timber.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.naman14.timber.R;
import com.naman14.timber.activities.BaseActivity;
import com.naman14.timber.adapters.SongsListAdapter;
import com.naman14.timber.dataloaders.MultimediaFileScanner;
import com.naman14.timber.dataloaders.SongLoader;
import com.naman14.timber.listeners.MusicStateListener;
import com.naman14.timber.models.Song;
import com.naman14.timber.utils.LogTool;
import com.naman14.timber.utils.PreferencesUtility;
import com.naman14.timber.utils.SortOrder;
import com.naman14.timber.widgets.DividerItemDecoration;
import com.naman14.timber.widgets.FastScroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SongsFragment extends Fragment implements MusicStateListener {

    private SongsListAdapter mAdapter;
    private RecyclerView recyclerView;
    private PreferencesUtility mPreferences;
    private List<Song> mSongList;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferencesUtility.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_recyclerview, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FastScroller fastScroller = (FastScroller) rootView.findViewById(R.id.fastscroller);
        fastScroller.setRecyclerView(recyclerView);

        new loadSongs().execute("");
        ((BaseActivity) getActivity()).setMusicStateListenerListener(this);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();
                isLoading = true;
//                        loading.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(Void result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                isLoading = false;
//                        loading.setVisibility(View.GONE);
            }

            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub
                MultimediaFileScanner.getInstance(getActivity()).startScanning(MEDIA_TYPE, MAX_DEEP, dirback);
                return null;
            }
        }.execute();


        /*stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MultimediaFileScanner.getInstance(getApplicationContext()).stopScanning();
            }
        });*/

        return rootView;
    }

    public void restartLoader() {

    }

    public void onPlaylistChanged() {

    }

    public void onMetaChanged() {
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    private void reloadAdapter() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... unused) {
                mSongList = SongLoader.getAllSongs(getActivity());
                mAdapter.updateDataSet(mSongList);

                //加载HQSong
                MultimediaFileScanner.getInstance(getActivity()).scanTheDirectory(null, mPath, MEDIA_TYPE, fileback);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.song_sort_by, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_by_az:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_A_Z);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_za:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_Z_A);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_artist:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_ARTIST);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_album:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_ALBUM);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_year:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_YEAR);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_duration:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_DURATION);
                reloadAdapter();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class loadSongs extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            if (getActivity() != null){
                mSongList = SongLoader.getAllSongs(getActivity());
                mAdapter = new SongsListAdapter((AppCompatActivity) getActivity(), mSongList, false);
                //加载HQSong

                MultimediaFileScanner.getInstance(getActivity()).scanTheDirectory(null, mPath, MEDIA_TYPE, fileback);
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            recyclerView.setAdapter(mAdapter);
            if (getActivity() != null)
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        }

        @Override
        protected void onPreExecute() {
        }
    }


    //add by hhl---------------------------------------------------------------------------------------------------------------------------------------

    MultimediaFileScanner.ScanCallback dirback = new MultimediaFileScanner.ScanCallback() {

        @Override
        public void onScanCompleted(boolean completed) {
            // TODO Auto-generated method stub
            LogTool.v("onScanCompleted-->" + completed);
//            handler.sendEmptyMessage(UPDATE_DATA);
        }

        @Override
        public void onError(Exception e, int code) {
            // TODO Auto-generated method stub
            LogTool.v("onError-->" + e.getMessage());
        }

        @Override
        public void onFind(HashMap<String, Object> device, String path) {
            // TODO Auto-generated method stub
            LogTool.v("onFind dir-->" + path);
//            dirs.add(new File(path));
        }
    };

    MultimediaFileScanner.ScanCallback fileback = new MultimediaFileScanner.ScanCallback() {

        @Override
        public void onScanCompleted(boolean completed) {
            // TODO Auto-generated method stub
            LogTool.v("onScan current dir Completed-->" + completed);
        }

        @Override
        public void onError(Exception e, int code) {
            // TODO Auto-generated method stub
            LogTool.v("onError-->" + e.getMessage());
        }

        @Override
        public void onFind(HashMap<String, Object> device, String path) {
            // TODO Auto-generated method stub
            LogTool.v("onFind MediaFile-->" + path);
            if(mSongList == null){
                mSongList = new ArrayList<>();
            }
            mSongList.add(getSongFromPath(path));
            onMetaChanged();
//            files.add(new File(path));
        }
    };

    private Song getSongFromPath(String path){
        Song md = new Song();
        String[] arrFile = path.split("\\.");
        String[] arrFileName = path.split("/");
        md.title = arrFile[arrFile.length -1];
        if(arrFileName != null && arrFileName.length > 0){
            md.title = arrFileName[arrFileName.length - 1].substring(0,arrFileName[arrFileName.length - 1].indexOf("."));
        }
        md.id = -100;
        md.duration = 0;
        md.artistName = "";
        md.path = path;
        return md;
    }

    boolean isLoading = false;
    private int MEDIA_TYPE = MultimediaFileScanner.TYPE_AUDIO;
    private int MAX_DEEP = 10;
    private String mPath = "/mnt/sdcard/Download";
}
