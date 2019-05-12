package androidteam.cs340.tickettoride.Shared;

public class Result {

    private boolean success;
    private String data;
    private String errorInfo;

    public Result(boolean success_, String data_, String errorInfo_) {
        success = success_;
        data = data_;
        errorInfo = errorInfo_;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getData() {
        return data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

}
