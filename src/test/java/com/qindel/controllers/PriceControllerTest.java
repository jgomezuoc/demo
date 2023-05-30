package com.qindel.controllers;

import com.qindel.constants.TestConstants;
import com.qindel.dtos.PriceDto;
import com.qindel.mappers.PriceMapper;
import com.qindel.requests.FindFinalPriceRequest;
import com.qindel.services.PriceService;
import com.qindel.utils.PriceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @MockBean
    private PriceMapper priceMapper;


    @Test
    void testFindAll() throws Exception {

        Instant now = Instant.now();
        FindFinalPriceRequest request = new FindFinalPriceRequest();
        request.setBrandId(1);
        request.setProductId(1);
        request.setDate(now);

        PriceDto priceDto = PriceUtils.createPriceDto();
        when(priceMapper.toDto(any())).thenReturn(priceDto);

        mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.PRICE_CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(TestConstants.BRAND_ID_PARAM_NAME, request.getBrandId().toString())
                        .param(TestConstants.PRODUCT_ID_PARAM_NAME, request.getProductId().toString())
                        .param(TestConstants.DATE_PARAM_NAME, now.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(priceDto.getBrandId()))
                .andExpect(jsonPath("$.price").value(priceDto.getPrice()));

        verify(priceService, times(1)).findFinalPrice(request);
    }


    @ParameterizedTest
    @CsvSource({
            "date, z",
            "brandId, a",
            "productId, q",
            "brandId, brandIdTestValue",
            "productId, productIdTestValue",
            "date, dateLongerStringTestValue",
    })

    void testFindAll_badRequest(String paramName, String paramValue) throws Exception {
        PriceDto priceDto = PriceUtils.createPriceDto();
        when(priceService.findFinalPrice(any())).thenReturn(PriceUtils.createPriceEntity());
        when(priceMapper.toDto(any())).thenReturn(priceDto);

        mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.PRICE_CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(paramName, paramValue))
                .andExpect(status().isBadRequest());

        verify(priceService, times(0)).findFinalPrice(any());
    }

}

