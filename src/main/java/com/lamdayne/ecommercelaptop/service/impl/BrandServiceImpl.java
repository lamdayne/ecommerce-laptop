package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.dto.request.BrandRequest;
import com.lamdayne.ecommercelaptop.dto.response.BrandResponse;
import com.lamdayne.ecommercelaptop.entity.Brand;
import com.lamdayne.ecommercelaptop.exception.AppException;
import com.lamdayne.ecommercelaptop.exception.ErrorCode;
import com.lamdayne.ecommercelaptop.mapper.BrandMapper;
import com.lamdayne.ecommercelaptop.repository.BrandRepository;
import com.lamdayne.ecommercelaptop.service.BrandService;
import com.lamdayne.ecommercelaptop.service.UploadImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final UploadImageFileService uploadImageFileService;

    @Override
    public BrandResponse createBrand(BrandRequest request) {
        Brand brand = brandMapper.toBrand(request);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }

    @Override
    public BrandResponse updateBrand(int id, BrandRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
        brandMapper.updateBrand(brand, request);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }

    @Override
    public void deleteBrand(int id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
        brandRepository.delete(brand);
    }

    @Override
    public BrandResponse getBrandById(int id) {
        return brandMapper.toBrandResponse(
                brandRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND))
        );
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        return brandMapper.toBrandResponseList(brandRepository.findAll());
    }

    @Override
    public BrandResponse uploadImageBrand(int id, MultipartFile file) throws IOException {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
        brand.setImageUrl(uploadImageFileService.uploadImageFile(file));
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }
}
