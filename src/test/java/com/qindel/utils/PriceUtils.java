package com.qindel.utils;

import com.qindel.dtos.PriceDto;
import com.qindel.entities.PriceEntity;
import com.qindel.requests.FindFinalPriceRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceUtils {
    public static FindFinalPriceRequest createValidFindFinalPriceRequest() {
        FindFinalPriceRequest findFinalPriceRequest = new FindFinalPriceRequest();
        findFinalPriceRequest.setBrandId(1);
        findFinalPriceRequest.setProductId(1);
        return findFinalPriceRequest;
    }

    public static FindFinalPriceRequest createInvalidFindFinalPriceRequest() {
        FindFinalPriceRequest findFinalPriceRequest = new FindFinalPriceRequest();
        findFinalPriceRequest.setBrandId(1);
        return findFinalPriceRequest;
    }

    public static Page<PriceEntity> createPriceEntityPage() {
        return new PageImpl<>(Collections.singletonList(createPriceEntity()));
    }

    public static PriceEntity createPriceEntity() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setBrandId(1L);
        priceEntity.setProductId(1L);
        priceEntity.setPriceList(1);
        priceEntity.setStartDate(Instant.ofEpochSecond(1));
        priceEntity.setEndDate(Instant.ofEpochSecond(2));
        priceEntity.setPrice(35.50);
        priceEntity.setPriority(0);
        priceEntity.setCurrency("EUR");
        return priceEntity;
    }

    public static PriceDto createPriceDto() {
        PriceDto priceDto = new PriceDto();
        priceDto.setBrandId(1L);
        priceDto.setProductId(1L);
        priceDto.setPriceList(1L);
        priceDto.setStartDate(Instant.ofEpochSecond(1));
        priceDto.setEndDate(Instant.ofEpochSecond(2));
        priceDto.setPrice(35.50);
        priceDto.setCurrency("EUR");
        return priceDto;
    }
}
