package androidteam.cs340.tickettoride.Shared;

public class Result {

    private int statusCode;
    private String data;
    private String errorInfo;

    public Result(int statuCode_, String data_, String errorInfo_) {
        statusCode = statuCode_;
        errorInfo = errorInfo_;
        data = data_;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getData() {
        return data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
