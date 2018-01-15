//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdk.concurrency.sparkle;

public class SpillOverException extends CommonRuntimeException {
    public SpillOverException() {
        super(EnumErrors.EXIT_OVERFLOW.getErrorCode(), EnumErrors.EXIT_OVERFLOW.getErrorMsg());
    }

    public SpillOverException(String msg) {
        super(EnumErrors.EXIT_OVERFLOW.getErrorCode(), msg);
    }
}
