package com.proj.co.uk.app;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

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

import com.proj.co.uk.app.service.CreditCardService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class CreditCardRestApplicationTests {
	
	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CreditCardService service;

	@Test
	public void contextLoads() throws Exception {
		
		when(service.findAll()).thenReturn(
				Collections.emptyList()
		);
		MvcResult mvcresult = mockmvc.perform(
				MockMvcRequestBuilders.get("/home/showall").accept(MediaType.APPLICATION_CBOR)
		).andReturn();
		System.out.println("mvcresultmvcresult"+mvcresult);
		verify(service).findAll();
	}

}