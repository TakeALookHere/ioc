package com.miskevich.ioc.testdata;

public class PaymentService {
    private EmailService emailService;
    private int maxAmount;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void pay(String from, String to, double amount) {
        emailService.sendEmail("from", "payment successful");
        emailService.sendEmail("to", "payment successful");
    }
}
