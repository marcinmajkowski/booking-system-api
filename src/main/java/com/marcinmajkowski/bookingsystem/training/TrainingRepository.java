package com.marcinmajkowski.bookingsystem.training;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TrainingRepository extends PagingAndSortingRepository<Training, Long> {
}
