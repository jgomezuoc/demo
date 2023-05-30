package com.qindel.controllers;

import com.qindel.constants.TestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("PriceController's Integration Tests")
    @ParameterizedTest(name = "Test case {index}: productId={0}, brandId={1}, date={2}")
    @CsvSource({
            "35455, 1, 2020-06-14T10:00:00Z, 35.5",
            "35455, 1, 2020-06-14T16:00:00Z, 25.45",
            "35455, 1, 2020-06-14T21:00:00Z, 35.5",
            "35455, 1, 2020-06-15T10:00:00Z, 30.5",
            "35455, 1, 2020-06-16T21:00:00Z, 38.95",
    })
    void integrationTests(String productId, String brandId, String date, String expectedPrice) throws Exception {

        this.mockMvc.perform(get(TestConstants.PRICE_CONTROLLER_PATH)
                        .param(TestConstants.PRODUCT_ID_PARAM_NAME, productId)
                        .param(TestConstants.BRAND_ID_PARAM_NAME, brandId)
                        .param(TestConstants.DATE_PARAM_NAME, date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }


}
