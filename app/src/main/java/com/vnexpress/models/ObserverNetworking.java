package com.vnexpress.models;

import android.util.Log;

import com.vnexpress.API.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ObserverNetworking implements Observer {
    public static ObserverNetworking instance = new ObserverNetworking();
   static  List<Obserable> obserables = new ArrayList<>();
    public static ObserverNetworking getInstance(){
        return instance;
    }
    public void notifyObserver(News news){
        Log.d("ObserverNetworking", "notifyObserver: "+obserables.size());
        for (Obserable obserable : obserables) {
           Log.d("ObserverNetworking", "notifyObserver: "+obserable);
            obserable.update(news);
        }
    }
    public void addObserable(Obserable obserable){
        obserables.add(obserable);
    }
    public void removeObserable(Obserable obserable){
        obserables.remove(obserable);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
