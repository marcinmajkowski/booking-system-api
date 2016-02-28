package com.marcinmajkowski.bookingsystem.booking;

import com.marcinmajkowski.bookingsystem.customer.Customer;
import com.marcinmajkowski.bookingsystem.customer.CustomerRepository;
import com.marcinmajkowski.bookingsystem.mail.MailService;
import com.marcinmajkowski.bookingsystem.training.Training;
import com.marcinmajkowski.bookingsystem.training.TrainingRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("${spring.data.rest.base-path}/bookings")
public class BookingController {

    private static final Log logger = LogFactory.getLog(BookingController.class);

    private final BookingRepository bookingRepository;

    private final TrainingRepository trainingRepository;

    private final CustomerRepository customerRepository;

    private final MailService mailService;

    @Autowired
    public BookingController(BookingRepository bookingRepository, TrainingRepository trainingRepository, CustomerRepository customerRepository, MailService mailService) {
        this.bookingRepository = bookingRepository;
        this.trainingRepository = trainingRepository;
        this.customerRepository = customerRepository;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Booking confirm(@PathVariable Long id, @RequestParam(name = "confirmationCode") UUID confirmationCode) throws NoSuchRequestHandlingMethodException {
        logger.info("Received: id = " + id + ", confirmationCode = " + confirmationCode);
        Booking booking = bookingRepository.findOne(id);
        if (booking == null) {
            throw new NoSuchRequestHandlingMethodException("confirm", BookingController.class);
        }
        return booking;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Booking put(@RequestBody Booking booking) throws NoSuchRequestHandlingMethodException {
        Training training = trainingRepository.findOne(booking.getTraining().getId());
        if (training == null) {
            throw new NoSuchRequestHandlingMethodException("confirm", BookingController.class);
        }

        Customer customer = customerRepository.findOne(booking.getCustomer().getId());
        if (customer == null) {
            throw new NoSuchRequestHandlingMethodException("confirm", BookingController.class);
        }

        booking.setTraining(training);
        booking.setCustomer(customer);
        booking.setConfirmationCode(UUID.randomUUID());
        booking.setTimestamp(new Date());
        booking.setConfirmed(Boolean.FALSE);

        logger.info("Saving: " + booking);
        booking = bookingRepository.save(booking);

        mailService.send(booking.confirmationMailMessage());

        return booking;
    }
}
