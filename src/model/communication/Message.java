package model.communication;

public class Message {
    private String senderLogin;
    private String receiverLogin;
    private String text;
    private String createdDate;

    public Message() {
    }

    public Message(String senderLogin, String receiverLogin, String text, String createdDate) {
        this.senderLogin = senderLogin;
        this.receiverLogin = receiverLogin;
        this.text = text;
        this.createdDate = createdDate;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public String getReceiverLogin() {
        return receiverLogin;
    }

    public String getText() {
        return text;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public void setReceiverLogin(String receiverLogin) {
        this.receiverLogin = receiverLogin;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Message: senderLogin=" + senderLogin +
                ", receiverLogin=" + receiverLogin +
                ", text=" + text +
                ", createdDate=" + createdDate;
    }
}