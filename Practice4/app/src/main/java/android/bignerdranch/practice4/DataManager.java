package android.bignerdranch.practice4;

import android.bignerdranch.practice4.Class.Album;

import java.util.ArrayList;

public class DataManager {

    private static DataManager instance;
    private ArrayList<Album> dataList;

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ArrayList<Album> getDataList() {
        return dataList;
    }

    public void addData(Album item) {
        dataList.add(item);
    }
}
