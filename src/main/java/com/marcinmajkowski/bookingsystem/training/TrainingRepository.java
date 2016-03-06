package com.marcinmajkowski.bookingsystem.training;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface TrainingRepository extends PagingAndSortingRepository<Training, Long> {

    @PreAuthorize("permitAll")
    List<Training> findByVisibleTrue();

    @Override
    @PreAuthorize("permitAll")
    Iterable<Training> findAll(Sort var1);

    @Override
    @PreAuthorize("permitAll")
    Page<Training> findAll(Pageable var1);
}
