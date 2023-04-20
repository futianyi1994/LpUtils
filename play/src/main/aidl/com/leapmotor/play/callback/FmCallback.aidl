// FmCallback.aidl
package com.leapmotor.play.callback;
import com.leapmotor.play.db.FmList;
import com.leapmotor.play.db.FmCollectList;

// Declare any non-default types here with import statements

interface FmCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onFmList(in List<FmList> fmLists);

    void onFmCollectList(in List<FmCollectList> fmCollectLists);

    void onError(String error);
}