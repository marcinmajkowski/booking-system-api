package com.marcinmajkowski.bookingsystem.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    //TODO ignore case
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("returnObject?.email == principal.username or hasRole('ROLE_ADMIN')")
    Customer findByEmail(@Param("email") String email);
}
