package com.leapmotor.utils.ui;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.leapmotor.baselib.net.http.HttpCallback;
import com.leapmotor.baselib.utils.SecondUtil;
import com.leapmotor.lpradio.model.bean.AlbumInfoBean;
import com.leapmotor.lpradio.model.bean.RecommendListBean;
import com.leapmotor.lputils.utils.FindViewUtlis;
import com.leapmotor.mediac11.Constants;
import com.leapmotor.mediac11.IMediaAidlInterface;
import com.leapmotor.mediac11.api.ApiInterface;
import com.leapmotor.mediac11.manager.MediaManager;
import com.leapmotor.onlineradio.model.BasePageResult;
import com.leapmotor.onlineradio.model.bean.ListeningHistory;
import com.leapmotor.onlineradio.model.bean.SubscribeInfo;
import com.leapmotor.play.annotation.MediaType;
import com.leapmotor.play.annotation.PlayMode;
import com.leapmotor.play.annotation.PlayState;
import com.leapmotor.play.body.AlbumBody;
import com.leapmotor.play.body.MediaBody;
import com.leapmotor.play.body.SeekBody;
import com.leapmotor.play.callback.FavCallback;
import com.leapmotor.play.db.FmCollectList;
import com.leapmotor.play.db.FmList;
import com.leapmotor.play.db.LpRadioPlayList;
import com.leapmotor.play.db.OnlineRadioBroadcastList;
import com.leapmotor.play.db.UdiskPlayList;
import com.leapmotor.play.db.UltimatetvPlayList;
import com.leapmotor.play.db.XmlyPlayList;
import com.leapmotor.play.listener.LocalDbListener;
import com.leapmotor.play.listener.PlayStateListener;
import com.leapmotor.play.listener.SeekListener;
import com.leapmotor.ultimatetv.entity.AlbumInfo;
import com.leapmotor.ultimatetv.entity.SearchSongList;
import com.leapmotor.utils.R;
import com.leapmotor.utils.utils.TLog;
import com.leapmotor.xmly.annotation.SortType;
import com.leapmotor.xmly.model.bean.AlbumRichBean;
import com.leapmotor.xmly.model.bean.HistoryPlayRecordFullBean;
import com.leapmotor.xmly.model.bean.RecommendInfoBean;
import com.leapmotor.xmly.model.page.AlbumSubscribedPage;
import com.leapmotor.xmly.model.page.HistoryPlayRecordFullPage;

import java.util.List;

public class MediaActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MediaActivity";
    private MediaManager.MServiceConnection conn;
    private IMediaAidlInterface iMediaAidlInterface;
    private SeekListener.Stub seekListener;
    private PlayStateListener.Stub playStateListener;
    private LocalDbListener.Stub localDbListener;
    private TextView tvTitle, tvSinger, tvGetLastMediaAlbumInfo;
    private TextView tvPlayPause;
    private TextView tvContent;
    private TextView tvPlayMode;
    private TextView tvIsFav;
    private TextView tvEndTime;
    private TextView tvStarTime;
    private SeekBar seekBar;
    private TextView tvIsBtAvailable;
    private EditText etSwitchMediaTab, etId;
    private Button playLikeMusic, playBluetoothMusicList, playHistory, playGuessLikeMusic, playNextGuessLikeMusic, playUdiskHistory;
    private SeekBody seekBody;
    private boolean seekFromUser;
    private MediaBody mediaBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TLog.d(TAG, "onCreate");
        setContentView(R.layout.activity_media);
        bindService();
        MediaManager.getInstance().setBinderDeathListener(this::bindService);
        initView();
    }

    private void bindService() {
        if (conn == null) {
            conn = new MediaManager.MServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    super.onServiceConnected(componentName, iBinder);
                    iMediaAidlInterface = MediaManager.getInstance().getiMediaAidlInterface();
                    if (iMediaAidlInterface != null) {
                        ApiInterface.getInstance().init(iMediaAidlInterface);
                        initData();
                    }
                }
            };
        }
        MediaManager.getInstance().bindService(this, conn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TLog.d(TAG, "onDestroy");
        MediaManager.getInstance().unBindService(this, conn, seekListener, playStateListener);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        try {
            if (iMediaAidlInterface != null) {
                if (id == R.id.tvGetLastMediaAlbumInfo) {
                    AlbumBody lastMediaAlbumInfo = iMediaAidlInterface.getLastMediaAlbumInfo(mediaBody.getType());
                    if (lastMediaAlbumInfo != null) {
                        String json = JsonUtils.formatJson(GsonUtils.toJson(lastMediaAlbumInfo));
                        tvContent.setText(json);
                        TLog.v(TAG, "getLastMediaAlbumInfo: " + json);
                    } else {
                        TLog.e(TAG, "getLastMediaAlbumInfom is null");
                    }
                } else if (id == R.id.tvPre) {
                    iMediaAidlInterface.pre();
                } else if (id == R.id.tvPlayPause) {
                    iMediaAidlInterface.pauseOrResume();
                } else if (id == R.id.tvNext) {
                    iMediaAidlInterface.next();
                } else if (id == R.id.tvPlayMode) {
                    setPlayMode(iMediaAidlInterface);
                } else if (id == R.id.tvIsFav) {
                    if (mediaBody != null) {
                        @MediaType int mediaType = mediaBody.getType();
                        /*if ("收藏".equals(tvIsFav.getText().toString())) {
                            ApiInterface
                                    .getInstance()
                                    .onFav(mediaType, null, new HttpCallback<String>() {
                                        @Override
                                        public void onSuccess(String s) {
                                            try {
                                                iMediaAidlInterface.notifyOnFavChange(mediaType, true);
                                            } catch (RemoteException e) {
                                                e.printStackTrace();
                                            }
                                            tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(s)));
                                        }
                                    });
                        } else {
                            ApiInterface
                                    .getInstance()
                                    .onUnFav(mediaType, null, new HttpCallback<String>() {
                                        @Override
                                        public void onSuccess(String s) {
                                            try {
                                                iMediaAidlInterface.notifyOnFavChange(mediaType, false);
                                            } catch (RemoteException e) {
                                                e.printStackTrace();
                                            }
                                            tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(s)));
                                        }
                                    });
                        }*/
                        ApiInterface
                                .getInstance()
                                .controlCurFav(mediaType, "收藏".equals(tvIsFav.getText().toString()), new HttpCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(s)));
                                    }
                                });
                    }
                } else if (id == R.id.tvIsBtAvailable) {
                    iMediaAidlInterface.playBtMusic();
                } else if (id == R.id.playLikeMusic) {
                    iMediaAidlInterface.playKgLikeMusic(0);
                } else if (id == R.id.playRecMusic) {
                    iMediaAidlInterface.playKgRecMusic(0);
                } else if (id == R.id.playBluetoothMusicList) {
                    iMediaAidlInterface.playKgBluetoothMusicList(0);
                } else if (id == R.id.playHistory) {
                    iMediaAidlInterface.playKgHistory(0);
                } else if (id == R.id.playGuessLikeMusic) {
                    iMediaAidlInterface.playGuessLikeMusic();
                } else if (id == R.id.playNextGuessLikeMusic) {
                    iMediaAidlInterface.playNextGuessLikeMusic();
                } else if (id == R.id.playUdiskHistory) {
                    iMediaAidlInterface.playUdiskHistory(0);
                } else if (id == R.id.playLpRadioListTimeProgram) {
                    iMediaAidlInterface.playLpRadioListTimeProgram(true);
                } else if (id == R.id.playLpRadioProgram) {
                    iMediaAidlInterface.playLpRadioProgram(etId.getText().toString(), 0);
                } else if (id == R.id.playXmlyByAlbumId) {
                    iMediaAidlInterface.playXmlyByAlbumId(Long.parseLong(etId.getText().toString()), 0, 4, SortType.ASC, 0);
                } else if (id == R.id.playOnlineRadioLocal) {
                    iMediaAidlInterface.playOnlineRadioLocal(Constants.ONLINE_RADIO_PAGE_FIRST, 4, 2);
                } else if (id == R.id.playOnlineRadioSub) {
                    iMediaAidlInterface.playOnlineRadioSub(Constants.ONLINE_RADIO_PAGE_FIRST, 4, 1);
                } else if (id == R.id.playOnlineRadioHis) {
                    iMediaAidlInterface.playOnlineRadioHis(1);
                } else if (id == R.id.getKgAllUltimatetvPlayList) {
                    ApiInterface
                            .getInstance()
                            .getKgAllUltimatetvPlayListByFd(new HttpCallback<List<UltimatetvPlayList>>() {
                                @Override
                                public void onSuccess(List<UltimatetvPlayList> ultimatetvPlayLists) {
                                    TLog.v(TAG, "getKgAllUltimatetvPlayListByFd: " + ultimatetvPlayLists.size());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(ultimatetvPlayLists)));
                                }
                            });
                } else if (id == R.id.getKgDailyRecPlayList) {
                    ApiInterface
                            .getInstance()
                            .getKgDailyRecPlayList(new HttpCallback<List<UltimatetvPlayList>>() {
                                @Override
                                public void onSuccess(List<UltimatetvPlayList> ultimatetvPlayLists) {
                                    TLog.v(TAG, "getKgDailyRecPlayList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(ultimatetvPlayLists))));
                                }
                            });
                } else if (id == R.id.getKgAlbumInfoList) {
                    ApiInterface
                            .getInstance()
                            .getKgAlbumInfoList(etId.getText().toString(), Constants.KG_PAGE_FIRST, 1, new HttpCallback<AlbumInfo>() {
                                @Override
                                public void onSuccess(AlbumInfo albumInfo) {
                                    TLog.v(TAG, "getKgAlbumInfoList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(albumInfo))));
                                }
                            });
                } else if (id == R.id.searchKgSingleSong) {
                    ApiInterface
                            .getInstance()
                            .searchKgSingleSong(Constants.KG_PAGE_FIRST, 5, "刘德华", new HttpCallback<SearchSongList>() {
                                @Override
                                public void onSuccess(SearchSongList searchSongList) {
                                    TLog.v(TAG, "searchKgSingleSong: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(searchSongList))));
                                    try {
                                        iMediaAidlInterface.insetPlayMediaByMediaInfo(MediaType.TYPE_ULTIMATETV, GsonUtils.toJson(searchSongList.getList()));
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                } else if (id == R.id.getAllLpRadioPlayList) {
                    ApiInterface
                            .getInstance()
                            .getAllLpRadioPlayList(new HttpCallback<List<LpRadioPlayList>>() {
                                @Override
                                public void onSuccess(List<LpRadioPlayList> lpRadioPlayLists) {
                                    TLog.v(TAG, "getAllLpRadioPlayList: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(lpRadioPlayLists)));
                                }
                            });
                } else if (id == R.id.getLpRadioListTimeProgram) {
                    ApiInterface
                            .getInstance()
                            .getLpRadioListTimeProgram(new HttpCallback<List<LpRadioPlayList>>() {
                                @Override
                                public void onSuccess(List<LpRadioPlayList> lpRadioPlayLists) {
                                    TLog.v(TAG, "getLpRadioListTimeProgram: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(lpRadioPlayLists)));
                                }
                            });
                } else if (id == R.id.getLpRadioAlbumInfo) {
                    ApiInterface
                            .getInstance()
                            .getLpRadioAlbumInfo(etId.getText().toString(), new HttpCallback<AlbumInfoBean>() {
                                @Override
                                public void onSuccess(AlbumInfoBean albumInfoBean) {
                                    TLog.v(TAG, "getLpRadioAlbumInfo: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(albumInfoBean)));
                                }
                            });
                } else if (id == R.id.getLpRadioRecommendList) {
                    ApiInterface
                            .getInstance()
                            .getLpRadioRecommendList(new HttpCallback<RecommendListBean>() {
                                @Override
                                public void onSuccess(RecommendListBean recommendListBean) {
                                    TLog.v(TAG, "getLpRadioRecommendList: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(recommendListBean)));
                                }
                            });
                } else if (id == R.id.getFmFreqFromDb) {
                    ApiInterface
                            .getInstance()
                            .getFmFreqFromDb(new HttpCallback<List<FmList>>() {
                                @Override
                                public void onSuccess(List<FmList> fmLists) {
                                    TLog.v(TAG, "getFmFreqFromDb: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(fmLists))));
                                }
                            });
                } else if (id == R.id.getFmCollectFreqFromDb) {
                    ApiInterface
                            .getInstance()
                            .getFmCollectFreqFromDb(new HttpCallback<List<FmCollectList>>() {
                                @Override
                                public void onSuccess(List<FmCollectList> fmCollectLists) {
                                    TLog.v(TAG, "getFmCollectFreqFromDb: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(fmCollectLists))));
                                }
                            });
                } else if (id == R.id.getAllOnlineRadioBroadcastList) {
                    ApiInterface
                            .getInstance()
                            .getAllOnlineRadioBroadcastList(new HttpCallback<List<OnlineRadioBroadcastList>>() {
                                @Override
                                public void onSuccess(List<OnlineRadioBroadcastList> onlineRadioBroadcastLists) {
                                    TLog.v(TAG, "getAllOnlineRadioBroadcastList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(onlineRadioBroadcastLists))));
                                }
                            });
                } else if (id == R.id.getOnlineRadioSubscribeList) {
                    ApiInterface
                            .getInstance()
                            .getOnlineRadioSubscribeList(Constants.ONLINE_RADIO_PAGE_FIRST, 4, new HttpCallback<BasePageResult<List<SubscribeInfo>>>() {
                                @Override
                                public void onSuccess(BasePageResult<List<SubscribeInfo>> listBasePageResult) {
                                    TLog.v(TAG, "getOnlineRadioSubscribeList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(listBasePageResult))));
                                }
                            });
                } else if (id == R.id.getOnlineRadioHistoryList) {
                    ApiInterface
                            .getInstance()
                            .getOnlineRadioHistoryList(new HttpCallback<List<ListeningHistory>>() {
                                @Override
                                public void onSuccess(List<ListeningHistory> listeningHistories) {
                                    TLog.v(TAG, "getOnlineRadioHistoryList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(listeningHistories))));
                                }
                            });
                } else if (id == R.id.getAllUdiskPlayList) {
                    ApiInterface
                            .getInstance()
                            .getAllUdiskPlayList(new HttpCallback<List<UdiskPlayList>>() {
                                @Override
                                public void onSuccess(List<UdiskPlayList> udiskPlayLists) {
                                    TLog.v(TAG, "getAllUdiskPlayList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(udiskPlayLists))));
                                }
                            });
                } else if (id == R.id.getAllXmlyPlayList) {
                    ApiInterface
                            .getInstance()
                            .getAllXmlyPlayList(new HttpCallback<List<XmlyPlayList>>() {
                                @Override
                                public void onSuccess(List<XmlyPlayList> xmlyPlayLists) {
                                    TLog.v(TAG, "getAllXmlyPlayList: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(xmlyPlayLists)));
                                }
                            });
                } else if (id == R.id.getXmlyAlbumInfo) {
                    ApiInterface
                            .getInstance()
                            .getXmlyAlbumInfo(Long.parseLong(etId.getText().toString()), null, new HttpCallback<AlbumRichBean>() {
                                @Override
                                public void onSuccess(AlbumRichBean albumRichBean) {
                                    TLog.v(TAG, "getXmlyAlbumInfo: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(albumRichBean)));
                                }
                            });
                } else if (id == R.id.getXmlyRecVehicle) {
                    ApiInterface
                            .getInstance()
                            .getXmlyRecVehicle(4, new HttpCallback<List<RecommendInfoBean>>() {
                                @Override
                                public void onSuccess(List<RecommendInfoBean> recommendInfoBeans) {
                                    TLog.v(TAG, "getXmlyRecVehicle: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(recommendInfoBeans)));
                                }
                            });
                } else if (id == R.id.getXmlySubAlbumsByUid) {
                    ApiInterface
                            .getInstance()
                            .getXmlySubAlbumsByUid(Constants.XMLY_FIRST_OFFSET, 4, new HttpCallback<AlbumSubscribedPage>() {
                                @Override
                                public void onSuccess(AlbumSubscribedPage albumSubscribedPage) {
                                    TLog.v(TAG, "getXmlySubAlbumsByUid: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(albumSubscribedPage)));
                                }
                            });
                } else if (id == R.id.getHisAlbumsByUid) {
                    ApiInterface
                            .getInstance()
                            .getHisAlbumsByUid(Constants.XMLY_FIRST_OFFSET, 4, -1, new HttpCallback<HistoryPlayRecordFullPage>() {
                                @Override
                                public void onSuccess(HistoryPlayRecordFullPage historyPlayRecordFullPage) {
                                    TLog.v(TAG, "getHisAlbumsByUid: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(historyPlayRecordFullPage)));
                                    HistoryPlayRecordFullBean historyPlayRecordFullBean = historyPlayRecordFullPage.getItems().get(0);
                                    ApiInterface.getInstance().playXmlyHis(historyPlayRecordFullBean);
                                }
                            });
                } else if (id == R.id.getMediaBody) {
                    MediaBody mediaBody = ApiInterface.getInstance().getMediaBody();
                    TLog.v(TAG, "getMediaBody: " + mediaBody.toString() + ", " + Thread.currentThread());
                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(mediaBody)));
                } else if (id == R.id.unRegisterSeekListener) {
                    if (seekListener != null) {
                        iMediaAidlInterface.unRegisterSeekListener(seekListener);
                    }
                } else if (id == R.id.unRegisterPlayStateListener) {
                    if (playStateListener != null) {
                        iMediaAidlInterface.unRegisterPlayStateListener(playStateListener);
                    }
                } else if (id == R.id.unRegisterLocalDbListener) {
                    if (localDbListener != null) {
                        iMediaAidlInterface.unRegisterLocalDbListener(localDbListener);
                    }
                }
            }
            if (id == R.id.tvFinish) {
                finish();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        FindViewUtlis.findViewById(this, R.id.tvPre).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvPlayPause).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvNext).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playRecMusic).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playLpRadioListTimeProgram).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playLpRadioProgram).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playXmlyByAlbumId).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playOnlineRadioLocal).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playOnlineRadioSub).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playOnlineRadioHis).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getKgAllUltimatetvPlayList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getKgDailyRecPlayList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getKgAlbumInfoList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.searchKgSingleSong).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getAllLpRadioPlayList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getLpRadioListTimeProgram).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getLpRadioAlbumInfo).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getLpRadioRecommendList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getFmFreqFromDb).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getFmCollectFreqFromDb).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getAllOnlineRadioBroadcastList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getOnlineRadioSubscribeList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getOnlineRadioHistoryList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getAllUdiskPlayList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getAllXmlyPlayList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getXmlyAlbumInfo).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getXmlyRecVehicle).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getXmlySubAlbumsByUid).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getHisAlbumsByUid).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getMediaBody).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.unRegisterSeekListener).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.unRegisterPlayStateListener).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvFinish).setOnClickListener(this);

        tvContent = FindViewUtlis.findViewById(this, R.id.tvContent);
        tvTitle = FindViewUtlis.findViewById(this, R.id.tvTitle);
        tvSinger = FindViewUtlis.findViewById(this, R.id.tvSinger);
        tvGetLastMediaAlbumInfo = FindViewUtlis.findViewById(this, R.id.tvGetLastMediaAlbumInfo);
        tvGetLastMediaAlbumInfo.setOnClickListener(this);
        tvPlayPause = FindViewUtlis.findViewById(this, R.id.tvPlayPause);
        tvPlayMode = FindViewUtlis.findViewById(this, R.id.tvPlayMode);
        tvIsFav = FindViewUtlis.findViewById(this, R.id.tvIsFav);
        tvPlayMode.setOnClickListener(this);
        tvIsFav.setOnClickListener(this);
        tvStarTime = FindViewUtlis.findViewById(this, R.id.tvStarTime);
        tvEndTime = FindViewUtlis.findViewById(this, R.id.tvEndTime);
        seekBar = FindViewUtlis.findViewById(this, R.id.seekBar);
        tvIsBtAvailable = FindViewUtlis.findViewById(this, R.id.tvIsBtAvailable);
        tvIsBtAvailable.setOnClickListener(this);
        etSwitchMediaTab = FindViewUtlis.findViewById(this, R.id.etSwitchMediaTab);
        etId = FindViewUtlis.findViewById(this, R.id.etId);
        playLikeMusic = FindViewUtlis.findViewById(this, R.id.playLikeMusic);
        playLikeMusic.setOnClickListener(this);
        playBluetoothMusicList = FindViewUtlis.findViewById(this, R.id.playBluetoothMusicList);
        playBluetoothMusicList.setOnClickListener(this);
        playHistory = FindViewUtlis.findViewById(this, R.id.playHistory);
        playHistory.setOnClickListener(this);
        playGuessLikeMusic = FindViewUtlis.findViewById(this, R.id.playGuessLikeMusic);
        playGuessLikeMusic.setOnClickListener(this);
        playNextGuessLikeMusic = FindViewUtlis.findViewById(this, R.id.playNextGuessLikeMusic);
        playNextGuessLikeMusic.setOnClickListener(this);
        playUdiskHistory = FindViewUtlis.findViewById(this, R.id.playUdiskHistory);
        playUdiskHistory.setOnClickListener(this);
        tvContent.setMovementMethod(new ScrollingMovementMethod());
        etSwitchMediaTab.setOnEditorActionListener((v, actionId, event) -> {
            try {
                iMediaAidlInterface.switchMediaTab(Integer.parseInt(v.getText().toString()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    private void initData() {
        try {
            if (iMediaAidlInterface != null) {
                if (seekListener == null) {
                    seekListener = new SeekListener.Stub() {
                        @Override
                        public void onSeek(SeekBody seekBody) throws RemoteException {
                            TLog.v(TAG, "onSeek: " + Thread.currentThread());
                            ThreadUtils.runOnUiThread(() -> {
                                MediaActivity.this.seekBody = seekBody;
                                if (seekBody.getDuration() > 0) {
                                    long positionMs = seekBody.getPosition();
                                    int positionS = ((int) (seekBody.getPosition() / 1000));
                                    int duration = seekBody.getDuration();
                                    seekBar.setMax(duration);
                                    if (!seekFromUser) {
                                        seekBar.setProgress(positionS);
                                    }
                                    int timeType = SecondUtil.getTimeType(duration);
                                    tvEndTime.setText(SecondUtil.format(duration, timeType));
                                    tvStarTime.setText(SecondUtil.format(positionS, timeType));
                                } else {
                                    seekBar.setMax(-1);
                                }
                            });
                        }

                        @Override
                        public void onError(String error) throws RemoteException {
                            TLog.e(TAG, error);
                        }
                    };
                    iMediaAidlInterface.registerSeekListener(seekListener);
                }
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seekFromUser = fromUser;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seekFromUser = false;
                        if (seekBody != null && seekBody.getDuration() > 0) {
                            int positionS = seekBar.getProgress();
                            int timeType = SecondUtil.getTimeType(seekBar.getMax());
                            tvStarTime.setText(SecondUtil.format(positionS, timeType));
                            try {
                                iMediaAidlInterface.onSeekTo(positionS * 1000 + 100);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        } else {
                            seekBar.setProgress(0);
                        }
                    }
                });

                MediaBody mediaBody = ApiInterface.getInstance().getMediaBody();
                tvTitle.setText(mediaBody.getTitle());
                tvSinger.setText(mediaBody.getType() == MediaType.TYPE_BLUETOOTH ? mediaBody.getArtist() + " - " + iMediaAidlInterface.getBtDeviceName() : mediaBody.getArtist());
                showPlayMode(iMediaAidlInterface.onPlayMode(mediaBody.getType()));
                tvPlayPause.setText(iMediaAidlInterface.isPlaying() ? "暂停" : "播放");
                queryKgIsLikeFromDb(mediaBody.getUniqueId());
                this.mediaBody = mediaBody;
                if (playStateListener == null) {
                    playStateListener = new PlayStateListener.Stub() {
                        @Override
                        public void onState(MediaBody mediaBody) throws RemoteException {
                            MediaActivity.this.mediaBody = mediaBody;
                            ThreadUtils.runOnUiThread(() -> {
                                tvTitle.setText(mediaBody.getName());
                                try {
                                    tvSinger.setText(mediaBody.getType() == MediaType.TYPE_BLUETOOTH ? mediaBody.getArtist() + " - " + iMediaAidlInterface.getBtDeviceName() : mediaBody.getArtist());
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                                queryKgIsLikeFromDb(mediaBody.getUniqueId());
                                switch (mediaBody.getState()) {
                                    case PlayState.STATE_PLAY:
                                        tvPlayPause.setText("暂停");
                                        break;
                                    case PlayState.STATE_PAUSE:
                                    case PlayState.STATE_STOP:
                                    default:
                                        tvPlayPause.setText("播放");
                                        break;
                                }
                            });
                        }

                        @Override
                        public void onPlayMode(int mediaType, int playMode) throws RemoteException {
                            ThreadUtils.runOnUiThread(() -> showPlayMode(playMode));

                        }

                        @Override
                        public void onFavStateChange(int mediaType, boolean isFav) throws RemoteException {
                            ThreadUtils.runOnUiThread(() -> tvIsFav.setText(isFav ? "取消收藏" : "收藏"));
                        }
                    };
                    iMediaAidlInterface.registerPlayStateListener(playStateListener);
                }

                if (localDbListener == null) {
                    localDbListener = new LocalDbListener.Stub() {
                        @Override
                        public void onKgUltimatetvLikeCount(int count) throws RemoteException {
                            ThreadUtils.runOnUiThread(() -> playLikeMusic.setText("播放酷狗我喜欢".concat(" : " + count)));
                            TLog.v(TAG, "onKgUltimatetvLikeCount: " + count);
                        }

                        @Override
                        public void onKgBluetoothMusicCount(int count) throws RemoteException {
                            ThreadUtils.runOnUiThread(() -> playBluetoothMusicList.setText("播放酷狗蓝牙音乐历史".concat(" : " + count)));
                            TLog.v(TAG, "onKgBluetoothMusicCount: " + count);
                        }

                        @Override
                        public void onKgHistoryCount(int count) throws RemoteException {
                            ThreadUtils.runOnUiThread(() -> playHistory.setText("播放酷狗历史".concat(" : " + count)));
                            TLog.v(TAG, "onKgHistoryCount: " + count);
                        }

                        @Override
                        public void onUdiskHistoryCount(int count) throws RemoteException {
                            ThreadUtils.runOnUiThread(() -> playUdiskHistory.setText("播放U盘音乐历史".concat(" : " + count)));
                            TLog.v(TAG, "onUdiskHistoryCount: " + count);
                        }

                        @Override
                        public void onError(String error) throws RemoteException {
                            TLog.e(TAG, error);
                        }
                    };
                    iMediaAidlInterface.registerLocalDbListener(localDbListener);
                }
                tvIsBtAvailable.setText(iMediaAidlInterface.isBtAvailable() ? "已连接蓝牙设备（可点击播放）：".concat(iMediaAidlInterface.getBtDeviceName()) : "还没连接蓝牙音乐");
                playLikeMusic.setText("播放酷狗我喜欢".concat(" : " + iMediaAidlInterface.getKgUltimatetvLikeCount()));
                playBluetoothMusicList.setText("播放酷狗蓝牙音乐历史".concat(" : " + iMediaAidlInterface.getKgBluetoothMusicCount()));
                playHistory.setText("播放酷狗历史".concat(" : " + iMediaAidlInterface.getKgHistoryCount()));
                playUdiskHistory.setText("播放U盘音乐历史".concat(" : " + iMediaAidlInterface.getUdiskHistoryCount()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void queryKgIsLikeFromDb(String songId) {
        try {
            iMediaAidlInterface.queryKgIsLikeFromDb(songId, new FavCallback.Stub() {
                @Override
                public void onFav(boolean isFav) throws RemoteException {
                    TLog.v(TAG, "onFav: " + Thread.currentThread());
                    ThreadUtils.runOnUiThread(() -> tvIsFav.setText(isFav ? "取消收藏" : "收藏"));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void showPlayMode(@PlayMode int playMode) {
        switch (playMode) {
            case PlayMode.RANDOM:
                tvPlayMode.setText("随机");
                break;
            case PlayMode.ONE:
                tvPlayMode.setText("单曲");
                break;
            case PlayMode.CYCLE:
            case PlayMode.NONE:
            default:
                tvPlayMode.setText("循环");
                break;
        }
    }

    private void setPlayMode(IMediaAidlInterface iMediaAidlInterface) {
        try {
            @MediaType int lastMediaType = iMediaAidlInterface.getLastMediaType();
            @PlayMode int playMode = iMediaAidlInterface.onPlayMode(lastMediaType);
            switch (playMode) {
                case PlayMode.RANDOM:
                    iMediaAidlInterface.setPlayMode(lastMediaType, PlayMode.ONE);
                    break;
                case PlayMode.ONE:
                    iMediaAidlInterface.setPlayMode(lastMediaType, PlayMode.CYCLE);
                    break;
                case PlayMode.CYCLE:
                case PlayMode.NONE:
                default:
                    iMediaAidlInterface.setPlayMode(lastMediaType, PlayMode.RANDOM);
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}