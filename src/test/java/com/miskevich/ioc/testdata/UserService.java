package com.miskevich.ioc.testdata;

public interface UserService {
    void setEmailService(EmailService emailService);
    void sendEmailWithUsersCount();
}
