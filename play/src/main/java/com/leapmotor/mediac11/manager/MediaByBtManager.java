package com.leapmotor.mediac11.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leapmotor.mediac11.Constants;
import com.leapmotor.mediac11.IBinderPool;
import com.leapmotor.mediac11.IMediaByBtAidlInterface;
import com.leapmotor.play.listener.PlayStateListener;
import com.leapmotor.play.listener.SeekListener;

import java.util.Locale;

/**
 * good programmer.
 *
 * @date : 2023/5/24 13:42
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class MediaByBtManager {
    public static final int BINDER_MEDIA_BY_BT = 1;
    private static final String TAG = "MediaByBtManager";
    private static IBinderPool mBinderPool;
    private static IMediaByBtAidlInterface iMediaAidlInterface;
    private static BinderDeathRecipient binderDeathRecipient;
    private static boolean isAlive;

    private MediaByBtManager() {
        isAlive = false;
    }

    public static MediaByBtManager getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    public static Intent getMediaServiceIntent() {
        return new Intent(Constants.MEDIA_SERVICE_ACTION).setPackage(Constants.MEDIA_SERVICE_PKG);
    }

    public static boolean isAlive() {
        return isAlive;
    }

    public static void release() {
        if (mBinderPool != null) {
            mBinderPool.asBinder().unlinkToDeath(mBinderPoolDeathRecipient, 0);
            mBinderPool = null;
            isAlive = false;
        }
    }

    public void bindService(@NonNull Context context, @NonNull MServiceConnection conn) {
        Log.i(TAG, "bindService start");
        boolean connected = context.bindService(getMediaServiceIntent(), conn, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "bindService : " + connected);
    }

    public void unBindService(@NonNull Context context, @NonNull MServiceConnection conn) {
        unBindService(context, conn, (IInterface) null);
    }

    public void unBindService(@NonNull Context context, @NonNull MServiceConnection conn, @Nullable IInterface... listener) {
        Log.i(TAG, "unBindService");
        if (iMediaAidlInterface != null) {
            if (listener != null) {
                try {
                    for (IInterface iInterface : listener) {
                        if (iInterface instanceof SeekListener) {
                            Log.i(TAG, "unRegisterSeekListener");
                            iMediaAidlInterface.unRegisterSeekListener((SeekListener) iInterface);
                        } else if (iInterface instanceof PlayStateListener) {
                            Log.i(TAG, "unRegisterPlayStateListener");
                            iMediaAidlInterface.unRegisterPlayStateListener((PlayStateListener) iInterface);
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        context.unbindService(conn);
        release();
    }

    @Nullable
    public IBinder queryBinder(int code) {
        Log.i(TAG, "queryBinder ：" + code);
        if (mBinderPool == null) {
            Log.w(TAG, "queryBinder mBinderPool is null");
            return null;
        }
        try {
            return mBinderPool.queryBinder(code);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public IMediaByBtAidlInterface getiMediaAidlInterface() {
        return iMediaAidlInterface;
    }

    public void setBinderDeathListener(@Nullable BinderDeathRecipient binderDeathRecipient) {
        MediaByBtManager.binderDeathRecipient = binderDeathRecipient;
    }

    public void removeBinderDeathListener() {
        binderDeathRecipient = null;
    }

    public interface BinderDeathRecipient extends IBinder.DeathRecipient {
        @Override
        void binderDied();
    }

    private static class SingleInstanceHolder {
        private static final MediaByBtManager INSTANCE = new MediaByBtManager();
    }

    public static class MServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (isAlive) {
                Log.w(TAG, "onServiceConnected already");
                return;
            }
            mBinderPool = IBinderPool.Stub.asInterface(iBinder);
            try {
                iBinder.linkToDeath(mBinderPoolDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            IBinder binder = MediaByBtManager.getInstance().queryBinder(MediaByBtManager.BINDER_MEDIA_BY_BT);
            if (binder != null) {
                Log.i(TAG, "queryBinder success !");
                iMediaAidlInterface = IMediaByBtAidlInterface.Stub.asInterface(binder);
                isAlive = true;
            } else {
                Log.e(TAG, "queryBinder binder is null !");
            }
            Log.i(TAG, String.format(Locale.getDefault(), "onServiceConnected : %s", componentName));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, String.format(Locale.getDefault(), "onServiceDisconnected : %s", componentName));
            release();
        }
    }

    private static final IBinder.DeathRecipient mBinderPoolDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.e(TAG, "binderDied");
            release();
            if (binderDeathRecipient != null) {
                binderDeathRecipient.binderDied();
            }
        }
    };
}
