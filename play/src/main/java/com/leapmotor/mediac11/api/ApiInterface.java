package com.leapmotor.mediac11.api;

import android.os.Bundle;
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
import com.leapmotor.xmly.annotation.SortType;
import com.leapmotor.xmly.model.ErrorResponse;
import com.leapmotor.xmly.model.bean.AlbumPayBean;
import com.leapmotor.xmly.model.bean.AlbumRichBean;
import com.leapmotor.xmly.model.bean.HistoryPlayRecordFullBean;
import com.leapmotor.xmly.model.bean.RecommendInfoBean;
import com.leapmotor.xmly.model.bean.TrackFullBean;
import com.leapmotor.xmly.model.page.AlbumSubscribedPage;
import com.leapmotor.xmly.model.page.HistoryPlayRecordFullPage;

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

    private ApiInterface() {

    }

    public static ApiInterface getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    public static void playXmlyHis(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HistoryPlayRecordFullBean historyPlayRecordFullBean) {
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
     * @param iMediaAidlInterface
     * @param mediaType
     * @param onPlayList
     * @param httpCallback
     * @deprecated use {@link #controlCurFav(IMediaAidlInterface, int, boolean, HttpCallback))} instead
     */
    @Deprecated
    public void onFav(@NonNull IMediaAidlInterface iMediaAidlInterface, @MediaType int mediaType, @Nullable Parcelable onPlayList, @Nullable HttpCallback<String> httpCallback) {
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
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "onFav : " + error);
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
     * @param iMediaAidlInterface
     * @param mediaType
     * @param onPlayList
     * @param httpCallback
     * @deprecated use {@link #controlCurFav(IMediaAidlInterface, int, boolean, HttpCallback))} instead
     */
    @Deprecated
    public void onUnFav(@NonNull IMediaAidlInterface iMediaAidlInterface, @MediaType int mediaType, @Nullable Parcelable onPlayList, @Nullable HttpCallback<String> httpCallback) {
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
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "onUnFav : " + error);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlCurFav(@NonNull IMediaAidlInterface iMediaAidlInterface, @MediaType int mediaType, boolean isFav, @Nullable HttpCallback<String> httpCallback) {
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
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(fail));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "controlCurFav : " + error);
                    if (httpCallback != null) {
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public MediaBody getMediaBody(@NonNull IMediaAidlInterface iMediaAidlInterface) {
        try {
            return iMediaAidlInterface.getMediaBody();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getKgAllUltimatetvPlayList(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<UltimatetvPlayList>> httpCallback) {
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
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(json));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgAllUltimatetvPlayList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getKgDailyRecPlayList(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<UltimatetvPlayList>> httpCallback) {
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
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(json));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgDailyRecPlayList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getKgAlbumInfoList(@NonNull IMediaAidlInterface iMediaAidlInterface, String albumId, int page, int size, @NonNull HttpCallback<AlbumInfo> httpCallback) {
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
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(json));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgAlbumInfoList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAllLpRadioPlayList(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<LpRadioPlayList>> httpCallback) {
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
                    Log.e(TAG, "getAllLpRadioPlayList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getLpRadioListTimeProgram(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<LpRadioPlayList>> httpCallback) {
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
                    Log.e(TAG, "getLpRadioListTimeProgram : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getLpRadioRecommendList(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<RecommendListBean> httpCallback) {
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
                    Log.e(TAG, "getLpRadioRecommendList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getLpRadioAlbumInfo(@NonNull IMediaAidlInterface iMediaAidlInterface, String albumId, @NonNull HttpCallback<AlbumInfoBean> httpCallback) {
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
                    Log.e(TAG, "getLpRadioAlbumInfo : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getFmFreqFromDb(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<FmList>> httpCallback) {
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
                    Log.e(TAG, "getFmFreqFromDb : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getFmCollectFreqFromDb(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<FmCollectList>> httpCallback) {
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
                    Log.e(TAG, "getFmCollectFreqFromDb : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAllOnlineRadioBroadcastList(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<OnlineRadioBroadcastList>> httpCallback) {
        try {
            iMediaAidlInterface.getAllOnlineRadioBroadcastList(new OnlineRadioCallback.Stub() {
                @Override
                public void onOnlineRadioBroadcastList(List<OnlineRadioBroadcastList> onlineRadioBroadcastLists) {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(onlineRadioBroadcastLists));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getAllOnlineRadioBroadcastList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAllUdiskPlayList(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<UdiskPlayList>> httpCallback) {
        try {
            iMediaAidlInterface.getAllUdiskPlayList(new UdiskCallback.Stub() {
                @Override
                public void onUdiskPlayList(List<UdiskPlayList> udiskPlayLists) throws RemoteException {
                    ThreadUtils.runOnUiThread(() -> httpCallback.onSuccess(udiskPlayLists));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getAllUdiskPlayList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAllXmlyPlayList(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<List<XmlyPlayList>> httpCallback) {
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
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getAllXmlyPlayList : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getAllXmlyPlayList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getXmlyRecVehicle(@NonNull IMediaAidlInterface iMediaAidlInterface, int limit, @NonNull HttpCallback<List<RecommendInfoBean>> httpCallback) {
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
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getXmlyRecVehicle : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getXmlyRecVehicle : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getXmlySubAlbumsByUid(@NonNull IMediaAidlInterface iMediaAidlInterface, long timeline, int offset, @NonNull HttpCallback<AlbumSubscribedPage> httpCallback) {
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
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getXmlySubAlbumsByUid : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getXmlySubAlbumsByUid : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getHisAlbumsByUid(@NonNull IMediaAidlInterface iMediaAidlInterface, int offset, int limit, int category_id, @NonNull HttpCallback<HistoryPlayRecordFullPage> httpCallback) {
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
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getHisAlbumsByUid : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getHisAlbumsByUid : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getXmlyAlbumInfo(@NonNull IMediaAidlInterface iMediaAidlInterface, long albumId, String rich_info, @NonNull HttpCallback<AlbumRichBean> httpCallback) {
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
                        httpCallback.onFailed(GsonUtils.fromJson(json, ErrorResponse.class));
                    } catch (Exception e) {
                        Log.e(TAG, "getXmlyAlbumInfo : " + e);
                        ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(e.getMessage())));
                    }
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getXmlyAlbumInfo : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public BluetoothInfoBean getBluetoothMusicInfo(@NonNull IMediaAidlInterface iMediaAidlInterface) {
        try {
            String json = iMediaAidlInterface.getBluetoothMusicInfo();
            return GsonUtils.fromJson(json, BluetoothInfoBean.class);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getKgSelfbuiltPlaylistList(@NonNull IMediaAidlInterface iMediaAidlInterface, @NonNull HttpCallback<PlaylistList> httpCallback) {
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
                    ThreadUtils.runOnUiThread(() -> httpCallback.onFailed(json));
                }

                @Override
                public void onError(String error) throws RemoteException {
                    Log.e(TAG, "getKgAlbumInfoList : " + error);
                    ThreadUtils.runOnUiThread(() -> httpCallback.onError(new ApiException(error)));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static class SingleInstanceHolder {
        private static final ApiInterface INSTANCE = new ApiInterface();
    }
}
