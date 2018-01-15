//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdk.concurrency.sparkle;

public class ExistException extends CommonRuntimeException {
    public ExistException() {
        super(EnumErrors.EXIT_EXITS.getErrorCode(), EnumErrors.EXIT_EXITS.getErrorMsg());
    }

    public ExistException(String keyName) {
        super(EnumErrors.EXIT_EXITS.getErrorCode(), keyName + " " + EnumErrors.EXIT_EXITS.getErrorMsg());
    }
}
