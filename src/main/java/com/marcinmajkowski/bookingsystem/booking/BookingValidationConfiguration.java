package com.marcinmajkowski.bookingsystem.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * This configuration is needed since validator automatic wiring does not work due to bug
 * https://jira.spring.io/browse/DATAREST-524
 */
@Configuration
public class BookingValidationConfiguration extends RepositoryRestConfigurerAdapter {

    @Autowired
    private BeforeCreateBookingValidator beforeCreateBookingValidator;

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", beforeCreateBookingValidator);
    }
}
