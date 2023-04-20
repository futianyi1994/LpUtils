// OnlineRadioCallback.aidl
package com.leapmotor.play.callback;
import com.leapmotor.play.db.OnlineRadioBroadcastList;

// Declare any non-default types here with import statements

interface OnlineRadioCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onOnlineRadioBroadcastList(in List<OnlineRadioBroadcastList> onlineRadioBroadcastLists);

    void onError(String error);

}