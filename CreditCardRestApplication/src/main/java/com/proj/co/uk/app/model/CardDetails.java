package com.proj.co.uk.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CardDetails {
	@Id
	@NotNull
	@Size(min = 2, max = 30)
	String customerName;
	@NotNull
	@Size(min = 4, max = 19)
	String cardNumber;
	int balance;
	int creditLimit;

	public CardDetails() {
		super();
	}

	public CardDetails(@NotNull @Size(min = 2, max = 30) String customerName,
			@NotNull @Size(min = 4, max = 19) String cardNumber, int balance, int creditLimit) {
		super();
		this.customerName = customerName;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.creditLimit = creditLimit;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
}
