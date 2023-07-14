// IBinderPool.aidl
package com.leapmotor.mediac11;

// Declare any non-default types here with import statements

interface IOnFileListener {
    void onFile(in ParcelFileDescriptor pfd);

    void onError(String error);
}