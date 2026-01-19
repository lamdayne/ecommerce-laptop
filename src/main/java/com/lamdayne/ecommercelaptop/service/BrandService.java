package com.lamdayne.ecommercelaptop.service;

import com.lamdayne.ecommercelaptop.dto.request.BrandRequest;
import com.lamdayne.ecommercelaptop.dto.response.BrandResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BrandService {
    BrandResponse createBrand(BrandRequest request);
    BrandResponse updateBrand(int id, BrandRequest request);
    void deleteBrand(int id);
    BrandResponse getBrandById(int id);
    List<BrandResponse> getAllBrands();
    BrandResponse uploadImageBrand(int id, MultipartFile file) throws IOException;
}
