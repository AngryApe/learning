//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdk.concurrency.sparkle;

public class CommonRuntimeException extends RuntimeException {
    private int errorCode;

    protected CommonRuntimeException() {
    }

    protected CommonRuntimeException(int errCode, String errMsg) {
        super(errMsg);
        this.errorCode = errCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
