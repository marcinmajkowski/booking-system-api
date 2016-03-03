package com.marcinmajkowski.bookingsystem.booking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import java.util.UUID;

@RestController
@RequestMapping("${spring.data.rest.base-path}/bookings")
public class BookingController {

    private static final Log logger = LogFactory.getLog(BookingController.class);

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    //TODO serve page
    @RequestMapping(value = "/{id}/confirmation", method = RequestMethod.GET)
    public String confirm(@PathVariable Long id, @RequestParam(name = "code") UUID confirmationCode) throws NoSuchRequestHandlingMethodException {
        Booking booking = bookingRepository.findOne(id);

        if (booking == null) {
            throw new NoSuchRequestHandlingMethodException("confirm", BookingController.class);
        }

        booking.setConfirmed(Boolean.TRUE);

        bookingRepository.save(booking);

        return "confirmed";
    }
}
