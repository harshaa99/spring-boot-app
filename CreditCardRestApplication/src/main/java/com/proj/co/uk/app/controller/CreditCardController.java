package com.proj.co.uk.app.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.proj.co.uk.app.model.CardDetails;
import com.proj.co.uk.app.service.CreditCardService;
import com.proj.co.uk.app.util.LuhnAlogrithm;

@RestController
@RequestMapping(value = "/home")
public class CreditCardController {
	private static final Logger logger = LoggerFactory.getLogger(CreditCardController.class);
	@Autowired
	CreditCardService service;

	@GetMapping(value = "/addcard")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("addcard");
		mv.addObject("cardDetails", loadCardDetails());
		return mv;
	}

	@PostMapping(value = "/showcard")
	public ModelAndView addCard(@Valid CardDetails cardDetails, BindingResult bResult) {
		logger.info("inside addCard method->>>>>");
		ModelAndView mv = new ModelAndView();
		List<CardDetails> details = service.findAll();
		mv.addObject("cardDetails", details);
		mv.setViewName("addcard");
		if (bResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return mv;
		}
		if (!LuhnAlogrithm.Validate(cardDetails.getCardNumber())) {
			mv.addObject("carderror", "You have entered wrong card details.");
			return mv;
		} else {
			logger.info("End of addCard method->>>>>>");
			service.save(cardDetails);
			details.add(cardDetails);
			return mv;
		}
	}

	@PostMapping(value = "/add")
	public List<CardDetails> addNewCard(CardDetails cardDetails) {
		service.save(cardDetails);
		return service.findAll();
	}

	@GetMapping(value = "/showall")
	public List<CardDetails> getAllCardDetails() {
		return service.findAll();
	}
	
	private List<CardDetails> loadCardDetails() {
		logger.info("nside loadCardDetails method->>>>>");
		List<CardDetails> creditCardlist = Arrays.asList(new CardDetails("Alice","6832 7979 9797 9799",-124,2000));
		service.saveAll(creditCardlist);
		creditCardlist = service.findAll();
		return creditCardlist;
	}
}
