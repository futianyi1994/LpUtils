// UdiskCallback.aidl
package com.leapmotor.play.callback;
import com.leapmotor.play.db.UdiskPlayList;

// Declare any non-default types here with import statements

interface UdiskCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onUdiskPlayList(in List<UdiskPlayList> udiskPlayLists);

    void onError(String error);

}