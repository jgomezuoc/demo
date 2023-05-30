package com.qindel.requests;

import com.qindel.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.*;

class FindFinalPriceRequestTest {

    @Test
    void preprocessAttributes_withNullDate_shouldSetCurrentDate() {
        FindFinalPriceRequest request = new FindFinalPriceRequest();
        request.preprocessAttributes();
        Instant currentDate = Instant.now();


        assertThat(request.getDate())
                .isNotNull()
                .isBeforeOrEqualTo(currentDate);

    }

    @Test
    void validate_withNullProductId_shouldThrowBadRequestException() {
        FindFinalPriceRequest request = new FindFinalPriceRequest();
        request.setBrandId(1);
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(request::validate);
    }

    @Test
    void validate_withNullBrandId_shouldThrowBadRequestException() {
        FindFinalPriceRequest request = new FindFinalPriceRequest();
        request.setProductId(1);
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(request::validate);
    }

    @Test
    void validate_withValidAttributes_shouldNotThrowException() {
        FindFinalPriceRequest request = new FindFinalPriceRequest();
        request.setProductId(1);
        request.setBrandId(1);

        assertThatCode(request::validate).doesNotThrowAnyException();
    }
}
