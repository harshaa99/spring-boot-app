package com.proj.co.uk.app.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
		logger.info("START of home method->>>>>");
		ModelAndView mv = new ModelAndView("addcard");
		mv.addObject("cardDetails", loadCardDetails());
		logger.info("END of home method->>>>>");
		return mv;
	}

	@PostMapping(value = "/showcard")
	public ModelAndView addCard(@Validated CardDetails cardDetails) {
		logger.info("START of addCard method->>>>>");
		ModelAndView mv = new ModelAndView();
		if (!LuhnAlogrithm.Validate(cardDetails.getCardNumber())) {
			mv.addObject("carderror", "You have entered wrong card details.");
			return mv;
		} 
		List<CardDetails> details = service.findAll();
		mv.addObject("cardDetails", details);
		mv.setViewName("addcard");
		service.save(cardDetails);
		details.add(cardDetails);
		logger.info("END of addCard method->>>>>>");
		return mv;
	}

	@PostMapping(value = "/add")
	public List<CardDetails> addNewCard(CardDetails cardDetails) {
		logger.info("START of addNewCard method->>>>>");
		service.save(cardDetails);
		return service.findAll();
	}

	@GetMapping(value = "/showall")
	public List<CardDetails> getAllCardDetails() {
		return service.findAll();
	}
	
	private List<CardDetails> loadCardDetails() {
		logger.info("START of loadCardDetails method->>>>>");
		List<CardDetails> creditCardlist = Arrays.asList(new CardDetails("Alice", "4244 4434 4343 4324",-124,2000));
		service.saveAll(creditCardlist);
		creditCardlist = service.findAll();
		logger.info("END of loadCardDetails method->>>>>");
		return creditCardlist;
	}
}
