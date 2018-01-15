//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ape.lock;

import platform.util.common.ExistException;
import platform.util.common.SpillOverException;
import platform.util.common.TimeUtility;

import java.util.concurrent.locks.ReentrantLock;

public final class SparkleWrapper {

    public static volatile long lastTime = 0L;
    public static volatile long lastSeqNo = 0L;
    public static volatile long currentSeqNo = 0L;
    public static volatile ReentrantLock mutexEx = new ReentrantLock();

    public SparkleWrapper() {
    }

    public static final int getCoderLength() {
        int tempLength = 144;
        return tempLength / 8;
    }

    public static final long getSequence() {
        return _getSequence();
    }

    public static final long getSequenceInMilliSecond() {
        long tmp = 0L;

        while (true) {
            tmp = _getSequence();
            if (tmp == 1L) {
                return tmp;
            }

            try {
                Thread.sleep(1L);
            } catch (InterruptedException var3) {
                ;
            }
        }
    }

    private static final long _getSequence() throws ExistException {
        long sequenceNo = 1L;
        long currentTime = TimeUtility.getTimeMillis();
        if (lastTime == currentTime) {
            mutexEx.lock();
            try {
                if (lastTime == currentTime) {
                    if (currentSeqNo == 65536L) {
                        throw new SpillOverException(
                                "current sequence N.O. " + Long.toString(currentSeqNo)
                                        + " is overflow!");
                    }

                    lastSeqNo = (long) (currentSeqNo++);
                    sequenceNo = currentSeqNo;
                } else {
                    lastTime = currentTime;
                    currentSeqNo = 1L;
                    lastSeqNo = 0L;
                }
            } finally {
                mutexEx.unlock();
            }
        } else {
            mutexEx.lock();
            try {
                lastTime = currentTime;
                currentSeqNo = 1L;
                lastSeqNo = 0L;
            } finally {
                mutexEx.unlock();
            }
        }

        return currentTime + sequenceNo;
    }
}
