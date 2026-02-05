package com.lamdayne.ecommercelaptop.controller.admin;



import com.lamdayne.ecommercelaptop.dto.request.BrandRequest;
import com.lamdayne.ecommercelaptop.dto.response.BrandResponse;
import com.lamdayne.ecommercelaptop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/brand")
public class BrandControllerAdmin {

    private final BrandService brandService;

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "admin/brand/brand-list";
    }

    // CREATE FORM
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("brand", new BrandRequest());
        return "admin/brand/brand-form-create";
    }

    // CREATE SUBMIT
    @PostMapping("/create")
    public String create(@ModelAttribute("brand") BrandRequest request,
                         @RequestParam("imageFile") MultipartFile file,
                         RedirectAttributes ra) throws IOException {

        BrandResponse brand = brandService.createBrand(request);

        if (file != null && !file.isEmpty()) {
            brandService.uploadImageBrand(brand.getId(), file);
        }

        ra.addFlashAttribute("success", "Thêm brand thành công");
        return "redirect:/admin/brand?success=true";
    }

    // UPDATE FORM
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("brand", brandService.getBrandById(id));
        return "admin/brand/brand-form-update";
    }

    // UPDATE SUBMIT
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,
                         @ModelAttribute("brand") BrandRequest request,
                         @RequestParam(value = "imageFile", required = false) MultipartFile file,
                         RedirectAttributes ra) throws IOException {

        brandService.updateBrand(id, request);

        if (file != null && !file.isEmpty()) {
            brandService.uploadImageBrand(id, file);
        }

        ra.addFlashAttribute("success", "Cập nhật brand thành công");
        return "redirect:/admin/brand?success=true";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        brandService.deleteBrand(id);
        ra.addFlashAttribute("success", "Xóa brand thành công");
        return "redirect:/admin/brand?success=true";
    }
}
