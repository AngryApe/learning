//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdk.concurrency.sparkle;

public enum EnumErrors {
    EXIT_SUCCEEDED("succeeded"),
    EXIT_INVALID_PARAMETER("invalid parameter"),
    EXIT_PARAMETERS_UNMATCH("params unmatch"),
    EXIT_OVERFLOW("memory overflow"),
    EXIT_OUT_RANGE("out of range"),
    EXIT_NULL("null"),
    EXIT_EMPTY("empty"),
    EXIT_BUSY("busy"),
    EXIT_TIMEDOUT("timed out"),
    EXIT_UNKNOWED_HOST("unknowed host"),
    EXIT_INVALID_IP("invalid IP"),
    EXIT_INVALID_FD("invalid file descriptor"),
    EXIT_FILD_NOT_FOUND("file not found"),
    EXIT_EXITS("exits"),
    EXIT_UNEXITS("un-exits"),
    EXIT_IO_ERROR("IO error"),
    EXIT_UNSUPPORTED("un-supported"),
    EXIT_ERROR_XML("error to parse XML"),
    EXIT_OTHER_FAILURE("other failure"),
    EXIT_INVALID_KEY("invalid key");

    private String errorMsg;
    private int errorCode;

    private EnumErrors(String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = this.ordinal();
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public static boolean IS_OK(int errorCode) {
        return errorCode == EXIT_SUCCEEDED.getErrorCode();
    }

    public static boolean IS_ERROR(int errorCode) {
        return errorCode != EXIT_SUCCEEDED.getErrorCode();
    }
}
