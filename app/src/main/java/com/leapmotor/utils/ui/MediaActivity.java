package com.leapmotor.utils.ui;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.leapmotor.baselib.net.http.HttpCallback;
import com.leapmotor.baselib.utils.SecondUtil;
import com.leapmotor.lpradio.model.bean.RecommendListBean;
import com.leapmotor.lputils.utils.FindViewUtlis;
import com.leapmotor.mediac11.Constants;
import com.leapmotor.mediac11.IMediaAidlInterface;
import com.leapmotor.mediac11.api.ApiInterface;
import com.leapmotor.mediac11.manager.MediaManager;
import com.leapmotor.play.annotation.MediaType;
import com.leapmotor.play.annotation.PlayMode;
import com.leapmotor.play.annotation.PlayState;
import com.leapmotor.play.body.MediaBody;
import com.leapmotor.play.body.SeekBody;
import com.leapmotor.play.callback.FavCallback;
import com.leapmotor.play.db.FmCollectList;
import com.leapmotor.play.db.FmList;
import com.leapmotor.play.db.LpRadioPlayList;
import com.leapmotor.play.db.OnlineRadioBroadcastList;
import com.leapmotor.play.db.UdiskPlayList;
import com.leapmotor.play.db.UltimatetvPlayList;
import com.leapmotor.play.listener.PlayStateListener;
import com.leapmotor.play.listener.SeekListener;
import com.leapmotor.utils.R;
import com.leapmotor.utils.utils.TLog;
import com.leapmotor.xmly.annotation.SortType;
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
    private TextView tvTitle;
    private TextView tvSinger;
    private TextView tvPlayPause;
    private TextView tvContent;
    private TextView tvPlayMode;
    private TextView tvIsFav;
    private TextView tvEndTime;
    private TextView tvStarTime;
    private SeekBar seekBar;
    private EditText etSwitchMediaTab;
    private EditText etLpRadioProgramId;
    private EditText etXmlyAlbumId;
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
                if (id == R.id.tvPre) {
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
                                    .onFav(iMediaAidlInterface, mediaType, null, new HttpCallback<String>() {
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
                                    .onUnFav(iMediaAidlInterface, mediaType, null, new HttpCallback<String>() {
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
                                .controlCurFav(iMediaAidlInterface, mediaType, "收藏".equals(tvIsFav.getText().toString()), new HttpCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(s)));
                                    }
                                });
                    }
                } else if (id == R.id.playLikeMusic) {
                    iMediaAidlInterface.playKgLikeMusic(0);
                } else if (id == R.id.playRecMusic) {
                    iMediaAidlInterface.playKgRecMusic(0);
                } else if (id == R.id.playBluetoothMusicList) {
                    iMediaAidlInterface.playKgBluetoothMusicList(0);
                } else if (id == R.id.playHistory) {
                    iMediaAidlInterface.playKgHistory(0);
                } else if (id == R.id.playLpRadioListTimeProgram) {
                    iMediaAidlInterface.playLpRadioListTimeProgram(true);
                } else if (id == R.id.playLpRadioProgram) {
                    iMediaAidlInterface.playLpRadioProgram(etLpRadioProgramId.getText().toString(), 0);
                } else if (id == R.id.playXmlyByAlbumId) {
                    iMediaAidlInterface.playXmlyByAlbumId(Long.parseLong(etXmlyAlbumId.getText().toString()), 0, 4, SortType.ASC, 0);
                } else if (id == R.id.playOnlineRadioLocal) {
                    iMediaAidlInterface.playOnlineRadioLocal(Constants.ONLINE_RADIO_PAGE_FIRST, 4, 2);
                } else if (id == R.id.playOnlineRadioSub) {
                    iMediaAidlInterface.playOnlineRadioSub(Constants.ONLINE_RADIO_PAGE_FIRST, 4, 1);
                } else if (id == R.id.playOnlineRadioHis) {
                    iMediaAidlInterface.playOnlineRadioHis(1);
                } else if (id == R.id.getKgAllUltimatetvPlayList) {
                    ApiInterface
                            .getInstance()
                            .getKgAllUltimatetvPlayList(iMediaAidlInterface, new HttpCallback<List<UltimatetvPlayList>>() {
                                @Override
                                public void onSuccess(List<UltimatetvPlayList> ultimatetvPlayLists) {
                                    TLog.v(TAG, "getKgAllUltimatetvPlayList: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(ultimatetvPlayLists)));
                                }
                            });
                } else if (id == R.id.getKgDailyRecPlayList) {
                    ApiInterface
                            .getInstance()
                            .getKgDailyRecPlayList(iMediaAidlInterface, new HttpCallback<List<UltimatetvPlayList>>() {
                                @Override
                                public void onSuccess(List<UltimatetvPlayList> ultimatetvPlayLists) {
                                    TLog.v(TAG, "getKgDailyRecPlayList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(ultimatetvPlayLists))));
                                }
                            });
                } else if (id == R.id.getLpRadioListTimeProgram) {
                    ApiInterface
                            .getInstance()
                            .getLpRadioListTimeProgram(iMediaAidlInterface, new HttpCallback<List<LpRadioPlayList>>() {
                                @Override
                                public void onSuccess(List<LpRadioPlayList> lpRadioPlayLists) {
                                    TLog.v(TAG, "getLpRadioListTimeProgram: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(lpRadioPlayLists)));
                                }
                            });
                } else if (id == R.id.getLpRadioRecommendList) {
                    ApiInterface
                            .getInstance()
                            .getLpRadioRecommendList(iMediaAidlInterface, new HttpCallback<RecommendListBean>() {
                                @Override
                                public void onSuccess(RecommendListBean recommendListBean) {
                                    TLog.v(TAG, "getLpRadioRecommendList: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(recommendListBean)));
                                }
                            });
                } else if (id == R.id.getFmFreqFromDb) {
                    ApiInterface
                            .getInstance()
                            .getFmFreqFromDb(iMediaAidlInterface, new HttpCallback<List<FmList>>() {
                                @Override
                                public void onSuccess(List<FmList> fmLists) {
                                    TLog.v(TAG, "getFmFreqFromDb: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(fmLists))));
                                }
                            });
                } else if (id == R.id.getFmCollectFreqFromDb) {
                    ApiInterface
                            .getInstance()
                            .getFmCollectFreqFromDb(iMediaAidlInterface, new HttpCallback<List<FmCollectList>>() {
                                @Override
                                public void onSuccess(List<FmCollectList> fmCollectLists) {
                                    TLog.v(TAG, "getFmCollectFreqFromDb: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(fmCollectLists))));
                                }
                            });
                } else if (id == R.id.getAllOnlineRadioBroadcastList) {
                    ApiInterface
                            .getInstance()
                            .getAllOnlineRadioBroadcastList(iMediaAidlInterface, new HttpCallback<List<OnlineRadioBroadcastList>>() {
                                @Override
                                public void onSuccess(List<OnlineRadioBroadcastList> onlineRadioBroadcastLists) {
                                    TLog.v(TAG, "getAllOnlineRadioBroadcastList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(onlineRadioBroadcastLists))));
                                }
                            });
                } else if (id == R.id.getAllUdiskPlayList) {
                    ApiInterface
                            .getInstance()
                            .getAllUdiskPlayList(iMediaAidlInterface, new HttpCallback<List<UdiskPlayList>>() {
                                @Override
                                public void onSuccess(List<UdiskPlayList> udiskPlayLists) {
                                    TLog.v(TAG, "getAllUdiskPlayList: " + Thread.currentThread());
                                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(udiskPlayLists))));
                                }
                            });
                } else if (id == R.id.getXmlyRecVehicle) {
                    ApiInterface
                            .getInstance()
                            .getXmlyRecVehicle(iMediaAidlInterface, 4, new HttpCallback<List<RecommendInfoBean>>() {
                                @Override
                                public void onSuccess(List<RecommendInfoBean> recommendInfoBeans) {
                                    TLog.v(TAG, "getXmlyRecVehicle: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(recommendInfoBeans)));
                                }
                            });
                } else if (id == R.id.getXmlySubAlbumsByUid) {
                    ApiInterface
                            .getInstance()
                            .getXmlySubAlbumsByUid(iMediaAidlInterface, Constants.XMLY_FIRST_OFFSET, 4, new HttpCallback<AlbumSubscribedPage>() {
                                @Override
                                public void onSuccess(AlbumSubscribedPage albumSubscribedPage) {
                                    TLog.v(TAG, "getXmlySubAlbumsByUid: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(albumSubscribedPage)));
                                }
                            });
                } else if (id == R.id.getHisAlbumsByUid) {
                    ApiInterface
                            .getInstance()
                            .getHisAlbumsByUid(iMediaAidlInterface, Constants.XMLY_FIRST_OFFSET, 4, -1, new HttpCallback<HistoryPlayRecordFullPage>() {
                                @Override
                                public void onSuccess(HistoryPlayRecordFullPage historyPlayRecordFullPage) {
                                    TLog.v(TAG, "getHisAlbumsByUid: " + Thread.currentThread());
                                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(historyPlayRecordFullPage)));
                                    HistoryPlayRecordFullBean historyPlayRecordFullBean = historyPlayRecordFullPage.getItems().get(0);
                                    ApiInterface.playXmlyHis(iMediaAidlInterface, historyPlayRecordFullBean);
                                }
                            });
                } else if (id == R.id.getMediaBody) {
                    MediaBody mediaBody = ApiInterface.getInstance().getMediaBody(iMediaAidlInterface);
                    TLog.v(TAG, "getMediaBody: " + Thread.currentThread());
                    tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(mediaBody)));
                } else if (id == R.id.unRegisterSeekListener) {
                    if (seekListener != null) {
                        iMediaAidlInterface.unRegisterSeekListener(seekListener);
                    }
                } else if (id == R.id.unRegisterPlayStateListener) {
                    if (playStateListener != null) {
                        iMediaAidlInterface.unRegisterPlayStateListener(playStateListener);
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
        FindViewUtlis.findViewById(this, R.id.playLikeMusic).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playRecMusic).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playBluetoothMusicList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playHistory).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playLpRadioListTimeProgram).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playLpRadioProgram).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playXmlyByAlbumId).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playOnlineRadioLocal).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playOnlineRadioSub).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.playOnlineRadioHis).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getKgAllUltimatetvPlayList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getKgDailyRecPlayList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getLpRadioListTimeProgram).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getLpRadioRecommendList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getFmFreqFromDb).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getFmCollectFreqFromDb).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getAllOnlineRadioBroadcastList).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getAllUdiskPlayList).setOnClickListener(this);
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
        tvPlayPause = FindViewUtlis.findViewById(this, R.id.tvPlayPause);
        tvPlayMode = FindViewUtlis.findViewById(this, R.id.tvPlayMode);
        tvIsFav = FindViewUtlis.findViewById(this, R.id.tvIsFav);
        tvPlayMode.setOnClickListener(this);
        tvIsFav.setOnClickListener(this);
        tvStarTime = FindViewUtlis.findViewById(this, R.id.tvStarTime);
        tvEndTime = FindViewUtlis.findViewById(this, R.id.tvEndTime);
        seekBar = FindViewUtlis.findViewById(this, R.id.seekBar);
        etSwitchMediaTab = FindViewUtlis.findViewById(this, R.id.etSwitchMediaTab);
        etLpRadioProgramId = FindViewUtlis.findViewById(this, R.id.etLpRadioProgramId);
        etXmlyAlbumId = FindViewUtlis.findViewById(this, R.id.etXmlyAlbumId);
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

                MediaBody mediaBody = ApiInterface.getInstance().getMediaBody(iMediaAidlInterface);
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