package com.qindel.mappers;

import com.qindel.dtos.PriceDto;
import com.qindel.entities.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceDto toDto(PriceEntity priceEntity);
}
