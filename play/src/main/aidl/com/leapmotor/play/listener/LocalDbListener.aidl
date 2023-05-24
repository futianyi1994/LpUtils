// LocalDbListener.aidl
package com.leapmotor.play.listener;

// Declare any non-default types here with import statements

interface LocalDbListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onKgUltimatetvLikeCount(int count);

    void onKgBluetoothMusicCount(int count);

    void onKgHistoryCount(int count);

    void onUdiskHistoryCount(int count);

    void onError(String error);

}