package com.marcinmajkowski.bookingsystem.booking;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.validation.annotation.Validated;


@RepositoryRestResource
public interface BookingRepository extends CrudRepository<Booking, Long> {
}
