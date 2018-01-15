//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdk.concurrency.sparkle;

public class InvalidParamException extends CommonRuntimeException {
    public InvalidParamException() {
        super(EnumErrors.EXIT_INVALID_PARAMETER.getErrorCode(), EnumErrors.EXIT_INVALID_PARAMETER.getErrorMsg());
    }

    public InvalidParamException(String paramName) {
        super(EnumErrors.EXIT_INVALID_PARAMETER.getErrorCode(), paramName + " " + EnumErrors.EXIT_INVALID_PARAMETER.getErrorMsg());
    }
}
