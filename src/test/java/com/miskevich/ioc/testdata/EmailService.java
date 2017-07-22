package com.miskevich.ioc.testdata;

public class EmailService {
    private String protocol;
    private int port;
    private boolean firstCheck;
    private int secondCheck;
    private String thirdCheck;
    private String fourthCheck;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void sendEmail(String emailTo, String content) {
        System.out.println("Sending email to: " + emailTo);
        System.out.println("With content: " + content);
    }

    public boolean isFirstCheck() {
        return firstCheck;
    }

    public void setFirstCheck(boolean firstCheck) {
        this.firstCheck = firstCheck;
    }

    public int getSecondCheck() {
        return secondCheck;
    }

    public void setSecondCheck(int secondCheck) {
        this.secondCheck = secondCheck;
    }

    public String getThirdCheck() {
        return thirdCheck;
    }

    public void setThirdCheck(String thirdCheck) {
        this.thirdCheck = thirdCheck;
    }

    public String getFourthCheck() {
        return fourthCheck;
    }

    public void setFourthCheck(String fourthCheck) {
        this.fourthCheck = fourthCheck;
    }
}
