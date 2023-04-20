// LpRadioCallback.aidl
package com.leapmotor.play.callback;
import com.leapmotor.play.db.LpRadioPlayList;

// Declare any non-default types here with import statements

interface LpRadioCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onLpRadioPlayList(in List<LpRadioPlayList> lpRadioPlayLists);

    void onRecommendList(String json);

    void onError(String error);

}