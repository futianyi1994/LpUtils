// UltimatetvCallback.aidl
package com.leapmotor.play.callback;
import com.leapmotor.play.db.UltimatetvPlayList;

// Declare any non-default types here with import statements

interface UltimatetvCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onUltimatetvPlayList(in List<UltimatetvPlayList> ultimatetvPlayLists);

    void onJsonData(String json);

    void onFail(String json);

    void onError(String error);

}