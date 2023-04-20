// IBinderPool.aidl
package com.leapmotor.mediac11;

// Declare any non-default types here with import statements

interface IBinderPool {
    IBinder queryBinder(int binderCode);
}