package com.leapmotor.mediac11.api;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.reflect.TypeToken;
import com.leapmotor.baselib.exception.ApiException;
import com.leapmotor.baselib.net.http.HttpCallback;
import com.leapmotor.baselib.utils.GsonUtils;
import com.leapmotor.baselib.utils.ThreadUtils;
import com.leapmotor.bluetooth.model.bean.BluetoothInfoBean;
import com.leapmotor.lpradio.model.bean.AlbumInfoBean;
import com.leapmotor.lpradio.model.bean.RecommendListBean;
import com.leapmotor.mediac11.IMediaAidlInterface;
import com.leapmotor.mediac11.IOnFileListener;
import com.leapmotor.onlineradio.model.BasePageResult;
import com.leapmotor.onlineradio.model.bean.ListeningHistory;
import com.leapmotor.onlineradio.model.bean.SubscribeInfo;
import com.leapmotor.play.annotation.MediaType;
import com.leapmotor.play.body.MediaBody;
import com.leapmotor.play.callback.FmCallback;
import com.leapmotor.play.callback.JsonCallback;
import com.leapmotor.play.callback.LpRadioCallback;
import com.leapmotor.play.callback.OnlineRadioCallback;
import com.leapmotor.play.callback.UdiskCallback;
import com.leapmotor.play.callback.UltimatetvCallback;
import com.leapmotor.play.callback.XmlyCallback;
import com.leapmotor.play.db.FmCollectList;
import com.leapmotor.play.db.FmList;
import com.leapmotor.play.db.LpRadioPlayList;
import com.leapmotor.play.db.OnlineRadioBroadcastList;
import com.leapmotor.play.db.UdiskPlayList;
import com.leapmotor.play.db.UltimatetvPlayList;
import com.leapmotor.play.db.XmlyPlayList;
import com.leapmotor.ultimatetv.entity.AlbumInfo;
import com.leapmotor.ultimatetv.entity.PlaylistList;
import com.leapmotor.ultimatetv.entity.SearchSongList;
import com.leapmotor.xmly.annotation.SortType;
import com.leapmotor.xmly.model.ErrorResponse;
import com.leapmotor.xmly.model.bean.AlbumPayBean;
import com.leapmotor.xmly.model.bean.AlbumRichBean;
import com.leapmotor.xmly.model.bean.HistoryPlayRecordFullBean;
import com.leapmotor.xmly.model.bean.RecommendInfoBean;
import com.leapmotor.xmly.model.bean.TrackFullBean;
import com.leapmotor.xmly.model.page.AlbumSubscribedPage;
import com.leapmotor.xmly.model.page.HistoryPlayRecordFullPage;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

/**
 * good programmer.
 *
 * @date : 2023-04-10 10:31
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ApiInterface {
    private static final String TAG = "ApiInterface";
    private static IMediaAidlInterface iMediaAidlInterface;

    private ApiInterface() {

    }

    public static ApiInterface getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    public void init(@NonNull IMediaAidlInterface mediaAidlInterface) {
        iMediaAidlInterface = mediaAidlInterface;
    }

    public void playXmlyHis(@NonNull HistoryPlayRecordFullBean historyPlayRecordFullBean) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            if (historyPlayRecordFullBean.getContent_type() == 1) {
                TrackFullBean track = historyPlayRecordFullBean.getTrack();
                if (track != null) {
                    AlbumPayBean album = historyPlayRecordFullBean.getAlbum();
                    if (album != null) {
                        long albumId = album.getId();
                        iMediaAidlInterface.playXmlyHisTrack(albumId, track.getId(), track.getCheckType(), track.getCheckPlayType(), SortType.ASC);
                    }
                }
            } else if (historyPlayRecordFullBean.getContent_type() == 2) {
                if (historyPlayRecordFullBean.getRadio() != null) {
                    iMediaAidlInterface.playXmlyHisRadio(historyPlayRecordFullBean.getRadio());
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Bundle getFavOptionBundle(@NonNull IMediaAidlInterface iMediaAidlInterface, @MediaType int mediaType, @Nullable Parcelable onPlayList) throws RemoteException {
        Bundle bundle;
        if (onPlayList == null) {
            bundle = iMediaAidlInterface.onPlayList(mediaType);
            bundle.setClassLoader(getClass().getClassLoader());
            onPlayList = bundle.getParcelable("onPlayList");
            bundle.clear();
        } else {
            bundle = new Bundle();
        }
        bundle.putParcelable("favMedia", onPlayList);
        return bundle;
    }

    /**
     * @param mediaType
     * @param onPlayList
     * @param httpCallback
     * @deprecated use {@link #controlCurFav(int, boolean, HttpCallback))} instead
     */
    @Deprecated
    public void onFav(@MediaType int mediaType, @Nullable Parcelable onPlayList, @Nullable HttpCallback<String> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            Bundle bundle = getFavOptionBundle(iMediaAidlInterface, mediaType, onPlayList);
            iMediaAidlInterface.onFav(mediaType, bundle, new JsonCallback.Stub() {
                @Override
                public void getJsonData(String jsonData) throws RemoteException {
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(jsonData));
                    }
                }

                @Override
                public void onFailed(String fail) throws RemoteException {
                    Log.e(TAG, "onFav onFailed : " + fail);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "onFav onError : " + error);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param mediaType
     * @param onPlayList
     * @param httpCallback
     * @deprecated use {@link #controlCurFav(int, boolean, HttpCallback))} instead
     */
    @Deprecated
    public void onUnFav(@MediaType int mediaType, @Nullable Parcelable onPlayList, @Nullable HttpCallback<String> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            Bundle bundle = getFavOptionBundle(iMediaAidlInterface, mediaType, onPlayList);
            iMediaAidlInterface.onUnFav(mediaType, bundle, new JsonCallback.Stub() {
                @Override
                public void getJsonData(String jsonData) throws RemoteException {
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(jsonData));
                    }
                }

                @Override
                public void onFailed(String fail) throws RemoteException {
                    Log.e(TAG, "onUnFav onFailed : " + fail);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "onUnFav onError : " + error);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlCurFav(@MediaType int mediaType, boolean isFav, @Nullable HttpCallback<String> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.controlCurFav(mediaType, isFav, new JsonCallback.Stub() {
                @Override
                public void getJsonData(String jsonData) throws RemoteException {
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(jsonData));
                    }
                }

                @Override
                public void onFailed(String fail) throws RemoteException {
                    Log.e(TAG, "controlCurFav onFailed : " + fail);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "controlCurFav onError : " + error);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public MediaBody getMediaBody() {
        if (!checkIMediaByBtAidlInterface()) {
            return null;
        }
        try {
            return iMediaAidlInterface.getMediaBody();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getKgAllUltimatetvPlayList(@NonNull HttpCallback<List<UltimatetvPlayList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getKgAllUltimatetvPlayList(new UltimatetvCallback.Stub() {
                @Override
                public void onUltimatetvPlayList(List<UltimatetvPlayList> ultimatetvPlayLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(ultimatetvPlayLists));
                }

                @Override
                public void onJsonData(String json) throws RemoteException {

                }

                @Override
                public void onFail(String json) throws RemoteException {
                    Log.e(TAG, "getKgAllUltimatetvPlayList onFail : " + json);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(json));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgAllUltimatetvPlayList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getKgAllUltimatetvPlayListByFd(@NonNull HttpCallback<List<UltimatetvPlayList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getKgAllUltimatetvPlayListByFd(new IOnFileListener.Stub() {
                @Override
                public void onFile(ParcelFileDescriptor pfd) throws RemoteException {
                    try {
                        FileInputStream fis = new FileInputStream(pfd.getFileDescriptor());
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] buf = new byte[1920 * 1080];
                        int len;
                        while ((len = fis.read(buf)) != -1) {
                            bos.write(buf, 0, len);
                        }
                        String jsonData = bos.toString();
                        List<UltimatetvPlayList> ultimatetvPlayLists = GsonUtils.fromJson(jsonData, new TypeToken<List<UltimatetvPlayList>>() {
                        }.getType());
                        ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(ultimatetvPlayLists));
                        bos.close();
                        fis.close();
                        pfd.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgAllUltimatetvPlayListByFd onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getKgDailyRecPlayList(@NonNull HttpCallback<List<UltimatetvPlayList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getKgDailyRecPlayList(new UltimatetvCallback.Stub() {
                @Override
                public void onUltimatetvPlayList(List<UltimatetvPlayList> ultimatetvPlayLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(ultimatetvPlayLists));
                }

                @Override
                public void onJsonData(String json) throws RemoteException {

                }

                @Override
                public void onFail(String json) throws RemoteException {
                    Log.e(TAG, "getKgDailyRecPlayList onFail : " + json);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(json));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgDailyRecPlayList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getKgAlbumInfoList(String albumId, int page, int size, @NonNull HttpCallback<AlbumInfo> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getKgAlbumInfoList(albumId, page, size, new UltimatetvCallback.Stub() {
                @Override
                public void onUltimatetvPlayList(List<UltimatetvPlayList> ultimatetvPlayLists) throws RemoteException {
                }

                @Override
                public void onJsonData(String json) throws RemoteException {
                    AlbumInfo albumInfo = GsonUtils.fromJson(json, AlbumInfo.class);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(albumInfo));
                }

                @Override
                public void onFail(String json) throws RemoteException {
                    Log.e(TAG, "getKgAlbumInfoList onFail : " + json);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(json));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgAlbumInfoList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAllLpRadioPlayList(@NonNull HttpCallback<List<LpRadioPlayList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getAllLpRadioPlayList(new LpRadioCallback.Stub() {
                @Override
                public void onLpRadioPlayList(List<LpRadioPlayList> lpRadioPlayLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(lpRadioPlayLists));
                }

                @Override
                public void onRecommendList(String json) throws RemoteException {
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getAllLpRadioPlayList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getLpRadioListTimeProgram(@NonNull HttpCallback<List<LpRadioPlayList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getLpRadioListTimeProgram(new LpRadioCallback.Stub() {
                @Override
                public void onLpRadioPlayList(List<LpRadioPlayList> lpRadioPlayLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(lpRadioPlayLists));
                }

                @Override
                public void onRecommendList(String json) throws RemoteException {
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getLpRadioListTimeProgram onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getLpRadioRecommendList(@NonNull HttpCallback<RecommendListBean> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getLpRadioRecommendList(new LpRadioCallback.Stub() {
                @Override
                public void onLpRadioPlayList(List<LpRadioPlayList> lpRadioPlayLists) throws RemoteException {

                }

                @Override
                public void onRecommendList(String json) throws RemoteException {
                    RecommendListBean recommendListBean = GsonUtils.fromJson(json, RecommendListBean.class);
                    ThreadUtils.runOnUiThread(() -> {
                        if (recommendListBean.isOk()) {
                            httpCallback.onSuccess(recommendListBean);
                            httpCallback.onComplete();
                        } else {
                            try {
                                Log.e(TAG, "getLpRadioRecommendList onFailed : " + json);
                                httpCallback.onFailed(recommendListBean);
                            } catch (Exception e) {
                                Log.e(TAG, "getLpRadioRecommendList : " + e);
                                ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                            }
                        }
                    });
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getLpRadioRecommendList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getLpRadioAlbumInfo(String albumId, @NonNull HttpCallback<AlbumInfoBean> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getLpRadioAlbumInfo(albumId, new LpRadioCallback.Stub() {
                @Override
                public void onLpRadioPlayList(List<LpRadioPlayList> lpRadioPlayLists) throws RemoteException {

                }

                @Override
                public void onRecommendList(String json) throws RemoteException {
                    AlbumInfoBean albumInfoBean = GsonUtils.fromJson(json, AlbumInfoBean.class);
                    ThreadUtils.runOnUiThread(() -> {
                        if (albumInfoBean.isOk()) {
                            httpCallback.onSuccess(albumInfoBean);
                            httpCallback.onComplete();
                        } else {
                            try {
                                Log.e(TAG, "getLpRadioAlbumInfo onFailed : " + json);
                                httpCallback.onFailed(albumInfoBean);
                            } catch (Exception e) {
                                Log.e(TAG, "getLpRadioAlbumInfo : " + e);
                                ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                            }
                        }
                    });
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getLpRadioAlbumInfo onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getFmFreqFromDb(@NonNull HttpCallback<List<FmList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getFmFreqFromDb(new FmCallback.Stub() {
                @Override
                public void onFmList(List<FmList> fmLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(fmLists));
                }

                @Override
                public void onFmCollectList(List<FmCollectList> fmCollectLists) throws RemoteException {
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getFmFreqFromDb onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getFmCollectFreqFromDb(@NonNull HttpCallback<List<FmCollectList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getFmCollectFreqFromDb(new FmCallback.Stub() {
                @Override
                public void onFmList(List<FmList> fmLists) throws RemoteException {

                }

                @Override
                public void onFmCollectList(List<FmCollectList> fmCollectLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(fmCollectLists));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getFmCollectFreqFromDb onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAllOnlineRadioBroadcastList(@NonNull HttpCallback<List<OnlineRadioBroadcastList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getAllOnlineRadioBroadcastList(new OnlineRadioCallback.Stub() {
                @Override
                public void onOnlineRadioBroadcastList(List<OnlineRadioBroadcastList> onlineRadioBroadcastLists) {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(onlineRadioBroadcastLists));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getAllOnlineRadioBroadcastList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getOnlineRadioSubscribeList(int pageNum, int pageSize, HttpCallback<BasePageResult<List<SubscribeInfo>>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getOnlineRadioSubscribeList(pageNum, pageSize, new JsonCallback.Stub() {
                @Override
                public void getJsonData(String jsonData) throws RemoteException {
                    if (httpCallback != null) {
                        BasePageResult listBasePageResult = GsonUtils.fromJson(jsonData, BasePageResult.class);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(listBasePageResult));
                    }
                }

                @Override
                public void onFailed(String fail) throws RemoteException {
                    Log.e(TAG, "getOnlineRadioSubscribeList onFailed : " + fail);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getOnlineRadioSubscribeList onError : " + error);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getOnlineRadioHistoryList(HttpCallback<List<ListeningHistory>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getOnlineRadioHistoryList(new JsonCallback.Stub() {
                @Override
                public void getJsonData(String jsonData) throws RemoteException {
                    if (httpCallback != null) {
                        List<ListeningHistory> listeningHistories = GsonUtils.fromJson(jsonData, new TypeToken<List<ListeningHistory>>() {
                        }.getType());
                        ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(listeningHistories));
                    }
                }

                @Override
                public void onFailed(String fail) throws RemoteException {
                    Log.e(TAG, "getOnlineRadioHistoryList onFailed : " + fail);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getOnlineRadioHistoryList onError : " + error);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAllUdiskPlayList(@NonNull HttpCallback<List<UdiskPlayList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getAllUdiskPlayList(new UdiskCallback.Stub() {
                @Override
                public void onUdiskPlayList(List<UdiskPlayList> udiskPlayLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(udiskPlayLists));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getAllUdiskPlayList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAllXmlyPlayList(@NonNull HttpCallback<List<XmlyPlayList>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getAllXmlyPlayListBySingle(new XmlyCallback.Stub() {
                @Override
                public void onXmlyPlayList(List<XmlyPlayList> xmlyPlayLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(xmlyPlayLists));
                }

                @Override
                public void onJsonData(String json) throws RemoteException {

                }

                @Override
                public void onFail(String json) throws RemoteException {
                    try {
                        Log.e(TAG, "getAllXmlyPlayList onFail : " + json);
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getAllXmlyPlayList : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getAllXmlyPlayList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getXmlyRecVehicle(int limit, @NonNull HttpCallback<List<RecommendInfoBean>> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getXmlyRecVehicle(limit, new XmlyCallback.Stub() {
                @Override
                public void onXmlyPlayList(List<XmlyPlayList> xmlyPlayLists) throws RemoteException {

                }

                @Override
                public void onJsonData(String json) throws RemoteException {
                    List<RecommendInfoBean> recommendInfoBeans = GsonUtils.fromJson(json, new TypeToken<List<RecommendInfoBean>>() {
                    }.getType());
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(recommendInfoBeans));
                }

                @Override
                public void onFail(String json) throws RemoteException {
                    try {
                        Log.e(TAG, "getXmlyRecVehicle onFail : " + json);
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getXmlyRecVehicle : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getXmlyRecVehicle onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getXmlySubAlbumsByUid(long timeline, int offset, @NonNull HttpCallback<AlbumSubscribedPage> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getXmlySubAlbumsByUid(timeline, offset, new XmlyCallback.Stub() {
                @Override
                public void onXmlyPlayList(List<XmlyPlayList> xmlyPlayLists) throws RemoteException {

                }

                @Override
                public void onJsonData(String json) throws RemoteException {
                    AlbumSubscribedPage albumSubscribedPage = GsonUtils.fromJson(json, AlbumSubscribedPage.class);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(albumSubscribedPage));
                }

                @Override
                public void onFail(String json) throws RemoteException {
                    try {
                        Log.e(TAG, "getXmlySubAlbumsByUid onFail: " + json);
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getXmlySubAlbumsByUid : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getXmlySubAlbumsByUid onError: " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getHisAlbumsByUid(int offset, int limit, int category_id, @NonNull HttpCallback<HistoryPlayRecordFullPage> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getHisAlbumsByUid(offset, limit, category_id, new XmlyCallback.Stub() {
                @Override
                public void onXmlyPlayList(List<XmlyPlayList> xmlyPlayLists) throws RemoteException {

                }

                @Override
                public void onJsonData(String json) throws RemoteException {
                    HistoryPlayRecordFullPage historyPlayRecordFullPage = GsonUtils.fromJson(json, HistoryPlayRecordFullPage.class);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(historyPlayRecordFullPage));
                }

                @Override
                public void onFail(String json) throws RemoteException {
                    try {
                        Log.e(TAG, "getHisAlbumsByUid onFail : " + json);
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getHisAlbumsByUid : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getHisAlbumsByUid onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getXmlyAlbumInfo(long albumId, String rich_info, @NonNull HttpCallback<AlbumRichBean> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getXmlyAlbumInfo(albumId, rich_info, new XmlyCallback.Stub() {
                @Override
                public void onXmlyPlayList(List<XmlyPlayList> xmlyPlayLists) throws RemoteException {

                }

                @Override
                public void onJsonData(String json) throws RemoteException {
                    AlbumRichBean albumRichBean = GsonUtils.fromJson(json, AlbumRichBean.class);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(albumRichBean));
                }

                @Override
                public void onFail(String json) throws RemoteException {
                    try {
                        Log.e(TAG, "getXmlyAlbumInfo onFail : " + json);
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getXmlyAlbumInfo : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getXmlyAlbumInfo onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public BluetoothInfoBean getBluetoothMusicInfo() {
        if (!checkIMediaByBtAidlInterface()) {
            return null;
        }
        try {
            String json = iMediaAidlInterface.getBluetoothMusicInfo();
            return GsonUtils.fromJson(json, BluetoothInfoBean.class);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getKgSelfbuiltPlaylistList(@NonNull HttpCallback<PlaylistList> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.getKgSelfbuiltPlaylistList(new UltimatetvCallback.Stub() {
                @Override
                public void onUltimatetvPlayList(List<UltimatetvPlayList> ultimatetvPlayLists) throws RemoteException {
                }

                @Override
                public void onJsonData(String json) throws RemoteException {
                    PlaylistList playlistList = GsonUtils.fromJson(json, PlaylistList.class);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(playlistList));
                }

                @Override
                public void onFail(String json) throws RemoteException {
                    Log.e(TAG, "getKgSelfbuiltPlaylistList onFail : " + json);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(json));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgSelfbuiltPlaylistList onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void searchKgSingleSong(int page, int size, String keyword, @NonNull HttpCallback<SearchSongList> httpCallback) {
        if (!checkIMediaByBtAidlInterface()) {
            return;
        }
        try {
            iMediaAidlInterface.searchKgSingleSong(page, size, keyword, new JsonCallback.Stub() {
                @Override
                public void getJsonData(String jsonData) throws RemoteException {
                    SearchSongList searchSongList = GsonUtils.fromJson(jsonData, SearchSongList.class);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(searchSongList));
                }

                @Override
                public void onFailed(String fail) throws RemoteException {
                    Log.e(TAG, "searchKgSingleSong onFailed : " + fail);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "searchKgSingleSong onError : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
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
        private static final ApiInterface INSTANCE = new ApiInterface();
    }
}
