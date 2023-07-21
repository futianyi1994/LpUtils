package com.leapmotor.mediac11.api;

import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

import com.leapmotor.mediac11.IMediaByBtAidlInterface;
import com.leapmotor.play.annotation.MediaType;
import com.leapmotor.play.body.MediaBody;
import com.leapmotor.play.callback.JsonCallback;
import com.leapmotor.play.listener.PlayStateListener;

/**
 * good programmer.
 *
 * @date : 2023/5/24 16:09
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ApiByBtInterface {
    private static final String TAG = "ApiByBtInterface";
    private static IMediaByBtAidlInterface iMediaAidlInterface;

    private ApiByBtInterface() {

    }

    public static ApiByBtInterface getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    public static void init(@NonNull IMediaByBtAidlInterface iMediaAidlInterface) {
        ApiByBtInterface.iMediaAidlInterface = iMediaAidlInterface;
    }

    public boolean isLogin(@MediaType int mediaType) {
        if (checkIMediaByBtAidlInterface()) {
            try {
                return iMediaAidlInterface.isLogin(mediaType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean judgeEnable(@MediaType int mediaType, boolean isTts, boolean isToast) {
        if (checkIMediaByBtAidlInterface()) {
            try {
                return iMediaAidlInterface.judgeEnable(mediaType, isTts, isToast);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean isPlayingByMediaType(@MediaType int mediaType) {
        if (checkIMediaByBtAidlInterface()) {
            try {
                return iMediaAidlInterface.isPlayingByMediaType(mediaType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void pauseOrResume() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.pauseOrResume();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void play() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.play();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.pause();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void pre() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.pre();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void next() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.next();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void playCurentList(@MediaType int mediaType, String songId) {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.playCurentList(mediaType, songId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getLastMediaType() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                return iMediaAidlInterface.getLastMediaType();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return MediaType.TYPE_CURRENT;
    }

    public MediaBody getMediaBody() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                return iMediaAidlInterface.getMediaBody();
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public void getPlayListByMediaType(String action, JsonCallback jsonCallback) {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.getPlayListByMediaType(action, jsonCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void playByMeidaType(@MediaType int mediaType) {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.playByMeidaType(mediaType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isBtAvailable() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                return iMediaAidlInterface.isBtAvailable();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void playBtMusic() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.playBtMusic();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBluetoothMusicInfo() {
        if (checkIMediaByBtAidlInterface()) {
            try {
                return iMediaAidlInterface.getBluetoothMusicInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void registerPlayStateListener(@NonNull PlayStateListener playStateListener) {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.registerPlayStateListener(playStateListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unRegisterPlayStateListener(@NonNull PlayStateListener playStateListener) {
        if (checkIMediaByBtAidlInterface()) {
            try {
                iMediaAidlInterface.unRegisterPlayStateListener(playStateListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkIMediaByBtAidlInterface() {
        boolean isInit = iMediaAidlInterface != null;
        if (!isInit) {
            Log.e(TAG, "iMediaAidlInterface is null please init first!");
        }
        return isInit;
    }

    private static class SingleInstanceHolder {
        private static final ApiByBtInterface INSTANCE = new ApiByBtInterface();
    }
}
