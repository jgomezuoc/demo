package com.qindel.services.impl;

import com.qindel.entities.PriceEntity;
import com.qindel.exceptions.EntityNotFoundException;
import com.qindel.repositories.PriceRepository;
import com.qindel.requests.FindFinalPriceRequest;
import com.qindel.services.PriceService;
import com.qindel.specifications.PriceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceEntity findFinalPrice(FindFinalPriceRequest request) {
        request.cleanAndValidate();
        return priceRepository.findAll(PriceSpecification.finalPrice(request), PageRequest.of(0, 1))
                .stream().findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }
}
