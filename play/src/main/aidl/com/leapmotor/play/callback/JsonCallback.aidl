// JsonCallback.aidl
package com.leapmotor.play.callback;

// Declare any non-default types here with import statements

interface JsonCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void getJsonData(String jsonData);

    void onFailed(String fail);

    void onError(String error);

}