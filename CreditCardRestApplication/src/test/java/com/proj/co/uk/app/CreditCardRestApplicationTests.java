package com.proj.co.uk.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.proj.co.uk.app.model.CardDetails;
import com.proj.co.uk.app.service.CreditCardService;
import com.proj.co.uk.app.util.LuhnAlogrithm;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CreditCardRestApplicationTests {

	@Autowired
	MockMvc mockmvc;

	@MockBean
	CreditCardService service;

	@Test
	public void contextLoads() throws Exception {
		when(service.findAll()).thenReturn(Collections.emptyList());
		MvcResult mvcresult = mockmvc
				.perform(MockMvcRequestBuilders.get("/home/showall").accept(MediaType.APPLICATION_CBOR)).andReturn();
		System.out.println("mvcresultmvcresult" + mvcresult);
		verify(service).findAll();
	}

	@Test
	public void getAllCardsTest () {
		when(service.findAll())
			.thenReturn(Stream.of(new CardDetails("bob", "4444 2222 3333 4444", 200,2000))
					.collect(Collectors.toList()));
		assertEquals(1, service.findAll().size());
	}
	
	@Test
	public void luhnAlgTest () {
		assertTrue(LuhnAlogrithm.Validate("4689582853001318"));
	}
	
	@Test
	public void luhnAlgNegativeTest () {
		assertFalse(LuhnAlogrithm.Validate("46895826653001318"));
	}

}
