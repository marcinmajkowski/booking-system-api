package com.marcinmajkowski.bookingsystem.booking;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marcinmajkowski.bookingsystem.customer.Customer;
import com.marcinmajkowski.bookingsystem.training.Training;
import org.springframework.mail.SimpleMailMessage;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Training training;

    @ManyToOne(optional = false)
    private Customer customer;

    @JsonIgnore
    @Column(nullable = false)
    private UUID confirmationCode;

    @JsonIgnore
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(nullable = false)
    private Boolean confirmed;

    public SimpleMailMessage confirmationMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getEmail());
        message.setSubject("Potwierdzenie rezerwacji na zajecia " + training.getName());
        message.setText(confirmationCode.toString());

        return message;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UUID getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(UUID confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", training=" + training +
                ", customer=" + customer +
                ", confirmationCode=" + confirmationCode +
                ", timestamp=" + timestamp +
                ", confirmed=" + confirmed +
                '}';
    }
}
