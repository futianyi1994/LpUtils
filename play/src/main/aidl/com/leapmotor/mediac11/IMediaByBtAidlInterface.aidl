// IMediaAidlInterface.aidl
package com.leapmotor.mediac11;
import android.os.Bundle;
import com.leapmotor.play.body.MediaBody;
import com.leapmotor.play.callback.JsonCallback;
import com.leapmotor.play.listener.PlayStateListener;


// Declare any non-default types here with import statements
interface IMediaByBtAidlInterface {

    boolean isLogin(int mediaType);

    boolean judgeEnable(int mediaType, boolean isTts, boolean isToast);

    boolean isPlayingByMediaType(int mediaType);

    void pauseOrResume();

    void play();

    void pause();

    void pre();

    void next();

    void playCurentList(int mediaType, String songId);

    int getLastMediaType();

    MediaBody getMediaBody();

    oneway void getPlayListByMediaType(String action, JsonCallback jsonCallback);

    void playByMeidaType(int mediaType);

    boolean isBtAvailable();

    void playBtMusic();

    String getBluetoothMusicInfo();

    void registerPlayStateListener(PlayStateListener playStateListener);

    void unRegisterPlayStateListener(PlayStateListener playStateListener);

}