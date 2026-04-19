package storage;

public class LogEntry {
    private String action;
    private String timestamp;
    private String userLogin;

    public LogEntry() {
    }

    public LogEntry(String action, String timestamp, String userLogin) {
        this.action = action;
        this.timestamp = timestamp;
        this.userLogin = userLogin;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "LogEntry: action=" + action +
                ", timestamp=" + timestamp +
                ", userLogin=" + userLogin;
    }
}