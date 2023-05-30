package com.qindel.requests;

import com.qindel.exceptions.BadRequestException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class FindFinalPriceRequest extends AbstractRequest {
    private Instant date;

    private Integer productId;

    private Integer brandId;

    @Override
    protected void preprocessAttributes() {
        if (date == null) {
            this.date = Instant.now();
        }
    }

    @Override
    protected void validate() {
        if (productId == null) {
            throw new BadRequestException("productId is required");
        }
        if (brandId == null) {
            throw new BadRequestException("brandId is required");
        }
    }
}
