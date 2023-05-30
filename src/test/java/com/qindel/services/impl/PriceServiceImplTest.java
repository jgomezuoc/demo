package com.qindel.services.impl;

import com.qindel.exceptions.BadRequestException;
import com.qindel.exceptions.EntityNotFoundException;
import com.qindel.repositories.PriceRepository;
import com.qindel.requests.FindFinalPriceRequest;
import com.qindel.utils.PriceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void testFindFinalPrice() {
        when(priceRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(PriceUtils.createPriceEntityPage());
        priceService.findFinalPrice(PriceUtils.createValidFindFinalPriceRequest());
        verify(priceRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void testFindFinalPrice_EntityNotFound() {
        when(priceRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(Page.empty());

        FindFinalPriceRequest request = PriceUtils.createValidFindFinalPriceRequest();
        assertThatThrownBy(() -> priceService.findFinalPrice(request))
                .isInstanceOf(EntityNotFoundException.class);

        verify(priceRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void testFindFinalPrice_InvalidRequest() {
        FindFinalPriceRequest request = PriceUtils.createInvalidFindFinalPriceRequest();
        assertThatThrownBy(() -> priceService.findFinalPrice(request))
                .isInstanceOf(BadRequestException.class);
        verify(priceRepository, times(0)).findAll(any(Specification.class), any(Pageable.class));
    }
}
