// PlayStateListener.aidl
package com.leapmotor.play.listener;
import com.leapmotor.play.body.MediaBody;

// Declare any non-default types here with import statements

interface PlayStateListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onState(in MediaBody mediaBody);

    void onPlayMode(int mediaType, int playMode);

    void onFavStateChange(int mediaType, boolean isFav);

}