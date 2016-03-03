package com.marcinmajkowski.bookingsystem.booking;

import com.marcinmajkowski.bookingsystem.mail.MailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RepositoryEventHandler(Booking.class)
public class BookingEventHandler {

    private final static Log logger = LogFactory.getLog(BookingEventHandler.class);

    @Autowired
    private MailService mailService;

    @HandleBeforeCreate
    public void handleBookingBeforeCreate(Booking booking) {
        booking.setConfirmationCode(UUID.randomUUID());
        booking.setConfirmed(Boolean.FALSE);
        booking.setTimestamp(new Date());
    }

    @HandleAfterCreate
    public void handleBookingAfterCreate(Booking booking) {
        BookingConfirmationMail confirmationMail = new BookingConfirmationMail(booking);
        mailService.send(confirmationMail.simpleMailMessage());
    }

    @HandleBeforeSave
    public void handleBookingBeforeSave(Booking booking) {
        logger.info("Booking before save " + booking);
    }

    @HandleAfterSave
    public void handleBookingAfterSave(Booking booking) {
        logger.info("Booking after save " + booking);
    }
}
