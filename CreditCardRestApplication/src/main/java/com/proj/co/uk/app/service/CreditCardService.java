package com.proj.co.uk.app.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.co.uk.app.model.CardDetails;

public interface CreditCardService extends JpaRepository<CardDetails, String>{

}
