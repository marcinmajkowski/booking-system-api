package com.marcinmajkowski.bookingsystem.booking;

import org.springframework.mail.SimpleMailMessage;

import java.util.Date;
import java.util.UUID;

public class BookingConfirmationMail {

    private final UUID confirmationCode;

    private final Long bookingId;

    private final String customerEmail;

    private final String customerName;

    private final String trainingName;

    private final Date trainingDate;

    private final String applicationUrl;

    public BookingConfirmationMail(Booking booking, String applicationUrl) {
        this.confirmationCode = booking.getConfirmationCode();
        this.bookingId = booking.getId();
        this.customerEmail = booking.getCustomer().getEmail();
        this.customerName = booking.getCustomer().getFirstName();
        this.trainingName = booking.getTraining().getName();
        this.trainingDate = booking.getTraining().getDate();
        this.applicationUrl = applicationUrl;
    }

    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customerEmail);
        message.setSubject(subject());
        message.setText(text());

        return message;
    }

    //TODO internationalization
    //TODO date formatting
    private String subject() {
        return "Booking confirmation for " + trainingName + " on " + trainingDate;
    }

    //TODO internationalization
    //TODO confirmation link
    private String text() {
        return "Hi " + customerName + "!"
                + "\n"
                + "\n"
                + "To confirm your booking, click the following link:"
                + "\n"
                + "\n"
                + applicationUrl + "/api/v1/bookings/" + bookingId + "/confirmation?code="
                + confirmationCode.toString()
                + "\n"
                + "\n"
                + "Regards.";
    }
}
