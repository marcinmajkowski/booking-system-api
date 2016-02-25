package com.marcinmajkowski.lepszyklub.reservation;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
}
