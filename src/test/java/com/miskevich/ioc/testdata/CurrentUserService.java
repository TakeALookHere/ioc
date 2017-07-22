package com.miskevich.ioc.testdata;

public class CurrentUserService implements UserService {
    private EmailService emailService;
    private boolean firstCheck;
    private int secondCheck;
    private String thirdCheck;
    private String fourthCheck;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendEmailWithUsersCount() {
        int numberOfUsersInSystem = getUsersCount();
        emailService.sendEmail("aaa@bbb.com", "there are " + numberOfUsersInSystem + " in the system");
    }

    public void userInit(){
        setFourthCheck("User init-method check");
    }

    private int getUsersCount() {
        return (int) (Math.random() * 1_000);
    }

    public EmailService getEmailService() {
        return emailService;
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
