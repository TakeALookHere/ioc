package com.miskevich.ioc.testdata;

public class UserService {
    private EmailService emailService;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendEmailWithUsersCount() {
        int numberOfUsersInSystem = getUsersCount();
        emailService.sendEmail("aaa@bbb.com", "there are " + numberOfUsersInSystem + " in the system");
    }

    private int getUsersCount() {
        return (int) (Math.random() * 1_000);
    }
}
