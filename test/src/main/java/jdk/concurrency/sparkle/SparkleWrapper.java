//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdk.concurrency.sparkle;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.locks.ReentrantLock;

public final class SparkleWrapper {
    private long lastTime = 0L;
    private long lastSeqNo = 0L;
    private long currentSeqNo = 0L;
    private volatile ReentrantLock mutexEx = new ReentrantLock();

    public SparkleWrapper() {
    }

    public static final int getCoderLength() {
        int tempLength = 144;
        return tempLength / 8;
    }

    public final long getSequence() {
        return _getSequence();
    }

    public final long getSequenceInMilliSecond() {
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

    public final long getLongCoder(int type) {
        try {
            int loop = 0;
            long lngIP = 0L;
            long lngTime = 0L;
            int processID = 0;
            long seq = 0L;

            while (loop++ < 3) {
                try {
                    seq = _getSequence();
                    lngIP = OSUtility.getLongIP();
                    lngTime = TimeUtility.getTimeSeconds();
                    processID = OSUtility.getCurrentProcessID();
                    break;
                } catch (ExistException var24) {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException var23) {
                        ;
                    }

                    if (loop >= 3) {
                        return -1L;
                    }
                }
            }

            int coderLength = getCoderLength() * 8;
            long longCoder = 0L;
            int skip = 56;
            lngTime &= -1L;
            long a = lngTime * (long) Math.pow(2.0D, (double) (coderLength - skip));
            skip = skip + 32;
            lngIP &= 255L;
            long b = lngIP * (long) Math.pow(2.0D, (double) (coderLength - skip));
            skip += 32;
            processID &= 255;
            long c = (long) processID * (long) Math.pow(2.0D, (double) (coderLength - skip));
            skip += 16;
            seq &= 255L;
            long d = seq * (long) Math.pow(2.0D, (double) (coderLength - skip));
            skip += 8;
            type &= 255;
            long e = (long) type * (long) Math.pow(2.0D, (double) (coderLength - skip));
            longCoder = a + b + c + d + e;
            return longCoder;
        } catch (SocketException var25) {
            ;
        } catch (UnknownHostException var26) {
            ;
        }

        return -1L;
    }

    public final String getCoder(int type) {
        long longCoder = getLongCoder(type);
        if (longCoder == -1L) {
            return null;
        } else {
            String stringCoder = Long.toString(longCoder);
            return stringCoder;
        }
    }

    private final long _getSequence() throws ExistException {
//        System.out.println(Thread.currentThread().getName()+"_"+mutexEx.hashCode());
        long sequenceNo = 1L;
        long currentTime = TimeUtility.getTimeSeconds();
        mutexEx.lock();
        try {
            if (lastTime == currentTime) {
                if (currentSeqNo == 65536L) {
                    throw new SpillOverException("current sequence N.O. " + Long.toString(currentSeqNo) + " is overflow!");
                }

                lastSeqNo = (long) (currentSeqNo++);
                sequenceNo = currentSeqNo;
            } else {
                lastTime = currentTime;
                currentSeqNo = 1L;
                lastSeqNo = 0L;
            }
            return currentTime * 100000 + sequenceNo;
        } finally {
            mutexEx.unlock();
        }

    }
}
