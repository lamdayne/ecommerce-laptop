package com.lamdayne.ecommercelaptop.mapper;

import com.lamdayne.ecommercelaptop.dto.request.BrandRequest;
import com.lamdayne.ecommercelaptop.dto.response.BrandResponse;
import com.lamdayne.ecommercelaptop.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toBrand(BrandRequest request);
    BrandResponse toBrandResponse(Brand brand);
    void updateBrand(@MappingTarget Brand brand, BrandRequest request);
    List<BrandResponse> toBrandResponseList(List<Brand> brands);
}
