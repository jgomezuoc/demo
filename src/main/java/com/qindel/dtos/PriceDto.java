package com.qindel.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PriceDto {
    private Long brandId;

    private Long priceList;

    private Long productId;

    private String currency;

    private Double price;

    private Instant endDate;

    private Instant startDate;

}
