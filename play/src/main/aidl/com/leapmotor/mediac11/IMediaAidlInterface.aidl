// IMediaAidlInterface.aidl
package com.leapmotor.mediac11;
import android.os.Bundle;
import com.leapmotor.play.body.MediaBody;
import com.leapmotor.play.callback.FavCallback;
import com.leapmotor.play.callback.JsonCallback;
import com.leapmotor.play.callback.LpRadioCallback;
import com.leapmotor.play.callback.UltimatetvCallback;
import com.leapmotor.play.callback.FmCallback;
import com.leapmotor.play.callback.OnlineRadioCallback;
import com.leapmotor.play.callback.UdiskCallback;
import com.leapmotor.play.callback.XmlyCallback;
import com.leapmotor.play.listener.PlayStateListener;
import com.leapmotor.play.listener.SeekListener;
import com.leapmotor.play.listener.LocalDbListener;
import com.leapmotor.xmly.model.bean.RadioBean;


// Declare any non-default types here with import statements
interface IMediaAidlInterface {
    void playLpRadioListTimeProgram(boolean forceRefreshProgram);

    void playLpRadioProgram(String albumId, int position);

    void playKgLikeMusic(int position);

    void playKgRecMusic(int position);

    void playKgBluetoothMusicList(int position);

    void playKgHistory(int position);

    void playXmlyByAlbumId(long id, int offset, int limit, String sort, int position);

    void playXmlyHisTrack(long albumId, long trackId, int checkType, int checkPlayType, String sort);

    void playXmlyHisRadio(in RadioBean radioBean);

    void playOnlineRadioLocal(int pageNum, int pageSize, int position);

    void playOnlineRadioSub(int pageNum, int pageSize, int position);

    void playOnlineRadioHis(int position);

    String getPlayingAlbumId();

    String getBtDeviceName();

    void switchMediaTab(int mediaType);

    boolean isLogin(int mediaType);

    boolean judgeEnable(int mediaType, boolean isTts, boolean isToast);

    boolean isPlaying();

    void pauseOrResume();

    void requestAudioFocus(int mediaType);

    void abandonAudioFocus(int mediaType);

    void play();

    void pause();

    void stop();

    void pre();

    void next();

    void skipToQueueItem(long position);

    boolean resumeLastPlay(int mediaType, boolean forceResume, boolean needResume);

    void onSeekTo(int seek);

    void setPlayMode(int mediaType, int playMode);

    int onPlayMode(int mediaType);

    oneway void queryKgIsLikeFromDb(String songId, FavCallback favCallback);

    oneway void onFav(int mediaType, in Bundle bundle, JsonCallback jsonCallBack);

    oneway void onUnFav(int mediaType, in Bundle bundle, JsonCallback jsonCallBack);

    oneway void controlCurFav(int mediaType, boolean isFav, JsonCallback jsonCallBack);

    void notifyOnFavChange(int mediaType, boolean isFav);

    Bundle onPlayList(int mediaType);

    int getCurrentIndex(int mediaType);

    oneway void hasLocalPlayList(int mediaType, JsonCallback jsonCallBack);

    void registerPlayStateListener(PlayStateListener playStateListener);

    void unRegisterPlayStateListener(PlayStateListener playStateListener);

    void registerSeekListener(SeekListener seekListener);

    void unRegisterSeekListener(SeekListener seekListener);

    int getLastMediaType();

    MediaBody getMediaBody();

    oneway void getKgAllUltimatetvPlayList(UltimatetvCallback ultimatetvCallback);

    oneway void getKgDailyRecPlayList(UltimatetvCallback ultimatetvCallback);

    oneway void getLpRadioListTimeProgram(LpRadioCallback lpRadioCallback);

    oneway void getLpRadioRecommendList(LpRadioCallback lpRadioCallback);

    oneway void getFmFreqFromDb(FmCallback fmCallback);

    oneway void getFmCollectFreqFromDb(FmCallback fmCallback);

    oneway void getAllOnlineRadioBroadcastList(OnlineRadioCallback fmCallback);

    oneway void getAllUdiskPlayList(UdiskCallback udiskCallback);

    oneway void getXmlyRecVehicle(int limit, XmlyCallback xmlyCallback);

    oneway void getXmlySubAlbumsByUid(long timeline, int offset, XmlyCallback xmlyCallback);

    oneway void getHisAlbumsByUid(int offset, int limit, int category_id, XmlyCallback xmlyCallback);

    void playByMeidaType(int mediaType);

    long getKgUltimatetvLikeCount();

    long getKgBluetoothMusicCount();

    long getKgHistoryCount();

    void registerLocalDbListener(LocalDbListener localDbListener);

    void unRegisterLocalDbListener(LocalDbListener localDbListener);

}