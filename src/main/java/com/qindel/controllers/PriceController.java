package com.qindel.controllers;

import com.qindel.dtos.PriceDto;
import com.qindel.mappers.PriceMapper;
import com.qindel.requests.FindFinalPriceRequest;
import com.qindel.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/price")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    private final PriceMapper priceMapper;

    /**
     * Retrieves the final price based on the given price specification.
     *
     * @param request the FindFinalPriceRequest to determine the final price
     * @return a PriceDto object representing the final price based on the given specification
     */
    @GetMapping
    PriceDto findAll(FindFinalPriceRequest request) {
        return this.priceMapper.toDto(priceService.findFinalPrice(request));
    }
}
