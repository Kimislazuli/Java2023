package edu.hw2.Task4;

public final class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        Exception ex = new Exception();
        StackTraceElement[] cause = ex.getStackTrace();
        return new CallingInfo(cause[1].getClassName(), cause[1].getMethodName());
    }

    public record CallingInfo(String className, String methodName) {}
}
