package com.leapmotor.utils.ui;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.leapmotor.lputils.utils.FindViewUtlis;
import com.leapmotor.lputils.utils.ThreadUtils;
import com.leapmotor.mediac11.IMediaByBtAidlInterface;
import com.leapmotor.mediac11.api.ApiByBtInterface;
import com.leapmotor.mediac11.manager.MediaByBtManager;
import com.leapmotor.play.annotation.MediaType;
import com.leapmotor.play.body.MediaBody;
import com.leapmotor.play.callback.JsonCallback;
import com.leapmotor.play.listener.PlayStateListener;
import com.leapmotor.utils.R;
import com.leapmotor.utils.utils.TLog;

public class MediaByBtActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MediaByBtActivity";
    private MediaByBtManager.MServiceConnection conn;
    private IMediaByBtAidlInterface iMediaAidlInterface;
    private TextView tvTitle;
    private TextView tvSinger;
    private TextView tvPlayPause;
    private TextView tvContent;
    private TextView tvIsBtAvailable;
    private EditText etSwitchMediaTab, getPlayListByMediaType;
    private MediaBody mediaBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TLog.d(TAG, "onCreate");
        setContentView(R.layout.activity_media_bybt);
        bindService();
        MediaByBtManager.getInstance().setBinderDeathListener(this::bindService);
        initView();
    }

    private void bindService() {
        if (conn == null) {
            conn = new MediaByBtManager.MServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    super.onServiceConnected(componentName, iBinder);
                    iMediaAidlInterface = MediaByBtManager.getInstance().getiMediaAidlInterface();
                    if (iMediaAidlInterface != null) {
                        ApiByBtInterface.init(iMediaAidlInterface);
                        initData();
                    }
                }
            };
        }
        MediaByBtManager.getInstance().bindService(this, conn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TLog.d(TAG, "onDestroy");
        MediaByBtManager.getInstance().unBindService(this, conn);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvPre) {
            ApiByBtInterface.getInstance().pre();
        } else if (id == R.id.tvPlayPause) {
            ApiByBtInterface.getInstance().pauseOrResume();
        } else if (id == R.id.tvNext) {
            ApiByBtInterface.getInstance().next();
        } else if (id == R.id.tvIsBtAvailable) {
            ApiByBtInterface.getInstance().playBtMusic();
        } else if (id == R.id.getCurrentPlayPosition) {
            if (mediaBody != null) {
                ApiByBtInterface
                        .getInstance()
                        .getCurrentPlayPosition(mediaBody.getType(), mediaBody.getUniqueId(), new JsonCallback.Stub() {
                            @Override
                            public void getJsonData(String jsonData) throws RemoteException {
                                TLog.v(TAG, "getCurrentPlayPosition: " + jsonData + ", " + Thread.currentThread());
                                ThreadUtils.runOnUiThread(() -> tvContent.setText(jsonData));
                            }

                            @Override
                            public void onFailed(String fail) throws RemoteException {
                                TLog.v(TAG, "getCurrentPlayPosition onFailed : " + fail + ", " + Thread.currentThread());
                            }

                            @Override
                            public void onError(String error) throws RemoteException {
                                TLog.v(TAG, "getCurrentPlayPosition onError : " + error + ", " + Thread.currentThread());
                            }
                        });
            }
        } else if (id == R.id.isVip) {
            if (mediaBody != null) {
                ApiByBtInterface
                        .getInstance()
                        .isVip(mediaBody.getType(), mediaBody.getUniqueId(), new JsonCallback.Stub() {
                            @Override
                            public void getJsonData(String jsonData) throws RemoteException {
                                TLog.v(TAG, "isVip: " + jsonData + ", " + Thread.currentThread());
                                ThreadUtils.runOnUiThread(() -> tvContent.setText(jsonData));
                            }

                            @Override
                            public void onFailed(String fail) throws RemoteException {
                                TLog.v(TAG, "isVip onFailed : " + fail + ", " + Thread.currentThread());
                            }

                            @Override
                            public void onError(String error) throws RemoteException {
                                TLog.v(TAG, "isVip onError : " + error + ", " + Thread.currentThread());
                            }
                        });
            }
        } else if (id == R.id.isKgGuessYouLike) {
            boolean isKgGuessYouLike = ApiByBtInterface.getInstance().isKgGuessYouLike();
            TLog.v(TAG, "isKgGuessYouLike: " + isKgGuessYouLike + ", " + Thread.currentThread());
            tvContent.setText(String.valueOf(isKgGuessYouLike));
        } else if (id == R.id.getMediaBody) {
            MediaBody mediaBody = ApiByBtInterface.getInstance().getMediaBody();
            TLog.v(TAG, "getMediaBody: " + mediaBody.toString() + ", " + Thread.currentThread());
            tvContent.setText(JsonUtils.formatJson(GsonUtils.toJson(mediaBody)));
        } else if (id == R.id.tvFinish) {
            finish();
        }
    }

    private void initView() {
        FindViewUtlis.findViewById(this, R.id.tvPre).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvNext).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getCurrentPlayPosition).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.isVip).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.isKgGuessYouLike).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.getMediaBody).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvFinish).setOnClickListener(this);

        tvPlayPause = FindViewUtlis.findViewById(this, R.id.tvPlayPause);
        tvPlayPause.setOnClickListener(this);
        tvIsBtAvailable = FindViewUtlis.findViewById(this, R.id.tvIsBtAvailable);
        tvIsBtAvailable.setOnClickListener(this);
        tvContent = FindViewUtlis.findViewById(this, R.id.tvContent);
        tvContent.setMovementMethod(new ScrollingMovementMethod());
        tvTitle = FindViewUtlis.findViewById(this, R.id.tvTitle);
        tvSinger = FindViewUtlis.findViewById(this, R.id.tvSinger);

        etSwitchMediaTab = FindViewUtlis.findViewById(this, R.id.etSwitchMediaTab);
        getPlayListByMediaType = FindViewUtlis.findViewById(this, R.id.getPlayListByMediaType);
        etSwitchMediaTab.setOnEditorActionListener((v, actionId, event) -> {
            ApiByBtInterface.getInstance().playByMeidaType(Integer.parseInt(v.getText().toString()));
            return false;
        });
        getPlayListByMediaType.setOnEditorActionListener((v, actionId, event) -> {
            QueryMediaBody queryMediaBody = new QueryMediaBody();
            QueryMediaBody.MusicListData musicList = new QueryMediaBody.MusicListData();
            musicList.setSourceId(String.valueOf(MediaType.TYPE_ULTIMATETV));
            musicList.setSize("5");
            musicList.setPage(v.getText().toString());
            queryMediaBody.setMusicList(musicList);
            ApiByBtInterface.getInstance().getPlayListByMediaType(GsonUtils.toJson(queryMediaBody), new JsonCallback.Stub() {
                @Override
                public void getJsonData(String jsonData) throws RemoteException {
                    TLog.v(TAG, "getPlayListByMediaType: " + jsonData + ", " + Thread.currentThread());
                    ThreadUtils.runOnUiThread(() -> tvContent.setText(JsonUtils.formatJson(jsonData)));
                }

                @Override
                public void onFailed(String fail) throws RemoteException {
                    TLog.v(TAG, "getPlayListByMediaType onFailed : " + fail + ", " + Thread.currentThread());
                }

                @Override
                public void onError(String error) throws RemoteException {
                    TLog.v(TAG, "getPlayListByMediaType onError : " + error + ", " + Thread.currentThread());
                }
            });
            return false;
        });
    }

    private void initData() {
        MediaBody mediaBody = ApiByBtInterface.getInstance().getMediaBody();
        updateMediaInfo(mediaBody);
        ApiByBtInterface.getInstance().registerPlayStateListener(new PlayStateListener.Stub() {
            @Override
            public void onState(MediaBody mediaBody) throws RemoteException {
                TLog.v(TAG, "onState: " + mediaBody.toString());
                ThreadUtils.runOnUiThread(() -> updateMediaInfo(mediaBody));
            }

            @Override
            public void onPlayMode(int mediaType, int playMode) throws RemoteException {

            }

            @Override
            public void onFavStateChange(int mediaType, boolean isFav) throws RemoteException {

            }
        });
    }

    private void updateMediaInfo(@Nullable MediaBody mediaBody) {
        if (mediaBody != null) {
            tvTitle.setText(mediaBody.getTitle());
            tvSinger.setText(mediaBody.getArtist());
            tvPlayPause.setText(ApiByBtInterface.getInstance().isPlayingByMediaType(mediaBody.getType()) ? "暂停" : "播放");
            this.mediaBody = mediaBody;
        }
    }

    public static class QueryMediaBody {

        private MusicListData musicList;

        public MusicListData getMusicList() {
            return musicList;
        }

        public void setMusicList(MusicListData musicList) {
            this.musicList = musicList;
        }

        public static class MusicListData {
            private String sourceId;
            private String size;
            private String page;

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }
        }
    }
}