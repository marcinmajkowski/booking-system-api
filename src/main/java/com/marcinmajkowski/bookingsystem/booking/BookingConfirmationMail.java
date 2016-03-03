package com.marcinmajkowski.bookingsystem.booking;

import org.springframework.mail.SimpleMailMessage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

public class BookingConfirmationMail {

    private final UUID confirmationCode;

    private final Long bookingId;

    private final String customerEmail;

    private final String customerName;

    private final String trainingName;

    private final Date trainingDate;

    public BookingConfirmationMail(Booking booking) {
        this.confirmationCode = booking.getConfirmationCode();
        this.bookingId = booking.getId();
        this.customerEmail = booking.getCustomer().getEmail();
        this.customerName = booking.getCustomer().getFirstName();
        this.trainingName = booking.getTraining().getName();
        this.trainingDate = booking.getTraining().getDate();
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

        //TODO need better solution here
        String hostName = "localhost";
        try {
            hostName = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "Hi " + customerName + "!"
                + "\n"
                + "\n"
                + "To confirm your booking, click the following link:"
                + "\n"
                + "\n"
                + "http://" + hostName + "/api/v1/bookings/" + bookingId + "/confirmation?code="
                + confirmationCode.toString()
                + "\n"
                + "\n"
                + "Regards.";
    }
}
