package com.example.tankorbox.myplayer.Classes;

import android.os.Environment;
import android.util.Log;

import com.example.tankorbox.myplayer.Data.OfflineSong;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by tankorbox on 8/14/2016.
 */
public class OfflineMusic {

    final String MEDIA_PATH = Environment.getExternalStorageDirectory()
            .getPath() + "/";
    private ArrayList<OfflineSong> songsList = new ArrayList<>();
    private String mp3Pattern = ".mp3";

    // Constructor
    public OfflineMusic() {
        Log.i("TAG7",MEDIA_PATH);
    }

    /**
     * Function to read all mp3 files and store the details in
     * ArrayList
     */

    public ArrayList<OfflineSong> getPlayList() {
        System.out.println(MEDIA_PATH);
        if (MEDIA_PATH != null) {
            File home = new File(MEDIA_PATH);
            File[] listFiles = home.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    System.out.println(file.getAbsolutePath());
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }
                }
            }
        }
        // return songs list array
        return songsList;
    }

    private void scanDirectory(File directory) {
        if (directory != null) {
            File[] listFiles = directory.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }
                }
            }
        }
    }

    private void addSongToList(File song) {
        if (song.getName().endsWith(mp3Pattern)) {
            OfflineSong data;
            data = new OfflineSong(song.getName().substring(0, song.getName().length() - 4),song.getPath());

            // Adding each song to SongList
            songsList.add(data);
        }
    }
}
