package com.qindel.services;

import com.qindel.entities.PriceEntity;
import com.qindel.exceptions.EntityNotFoundException;
import com.qindel.requests.FindFinalPriceRequest;

public interface PriceService {

    /**
     * Finds the final price based on the given request.
     *
     * @param request the FindFinalPriceRequest object containing the search criteria
     * @return the PriceEntity representing the final price
     * @throws EntityNotFoundException if no final price is found
     */
    PriceEntity findFinalPrice(FindFinalPriceRequest request);

}
