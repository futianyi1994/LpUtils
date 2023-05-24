// XmlyCallback.aidl
package com.leapmotor.play.callback;
import com.leapmotor.play.db.XmlyPlayList;

// Declare any non-default types here with import statements

interface XmlyCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onXmlyPlayList(in List<XmlyPlayList> xmlyPlayLists);

    void onJsonData(String json);

    void onFail(String json);

    void onError(String error);

}