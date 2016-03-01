package com.marcinmajkowski.bookingsystem.booking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateBookingValidator")
public class BeforeCreateBookingValidator implements Validator {

    private static final Log logger = LogFactory.getLog(BeforeCreateBookingValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return Booking.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        logger.info("Validation for Booking called!");
    }
}
