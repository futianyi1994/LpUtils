// SeekListener.aidl
package com.leapmotor.play.listener;
import com.leapmotor.play.body.SeekBody;

// Declare any non-default types here with import statements

interface SeekListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onSeek(in SeekBody seekBody);

    void onError(String error);

}