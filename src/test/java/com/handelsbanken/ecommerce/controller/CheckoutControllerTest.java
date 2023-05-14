package com.handelsbanken.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.handelsbanken.ecommerce.dto.PurchasePriceDTO;
import com.handelsbanken.ecommerce.dto.WatchInDTO;
import com.handelsbanken.ecommerce.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(CheckoutController.class)
class CheckoutControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CheckoutService service;

    @Test
    void whenValidInputReturnHttpStatusOK() throws Exception {

        List<WatchInDTO> dtos = List.of(new WatchInDTO(1),
                                        new WatchInDTO(2),
                                        new WatchInDTO(1),
                                        new WatchInDTO(4),
                                        new WatchInDTO(3));

        given(service.getTotalPriceAtCheckout(dtos))
                .willReturn(new PurchasePriceDTO(360));

        mockMvc.perform(post("/checkout")
                        .content(objectMapper.writeValueAsString(Arrays.asList(1,2,1,4,3)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}