package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PricingServiceApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<Price> json;

	@MockBean
	private PricingService pricingService;

	@Test
	public void contextLoads() {
	}

	//get single car price
	@Test
	public void getSingleCarPrice() throws Exception {

		mvc.perform(get(new URI("/services/price?vehicleId=2")))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.currency").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").value(2))
				.andDo(print());
	}
}
