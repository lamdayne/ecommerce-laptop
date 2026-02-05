package com.lamdayne.ecommercelaptop.controller.admin;

import com.lamdayne.ecommercelaptop.dto.request.CategoryRequest;
import com.lamdayne.ecommercelaptop.entity.Category;
import com.lamdayne.ecommercelaptop.repository.CategoryRepository;
import com.lamdayne.ecommercelaptop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class CategoryControllerAdmin {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String categoryForm() {
        return "/admin/category/category-form-create";
    }

    @GetMapping("/{categoryId}")
    public String editCategory(Model model, @PathVariable("categoryId") int categoryId) {
        model.addAttribute("category", categoryService.getCategoryById(categoryId));
        return "/admin/category/category-form-update";
    }

    @GetMapping("/list")
    public String listCategory(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        model.addAttribute("page", categoryPage);
        return "/admin/category/category-list";
    }

    @PostMapping
    public String addCategory(CategoryRequest request, RedirectAttributes redirectAttributes) {
        categoryService.createCategory(request);
        redirectAttributes.addFlashAttribute("message", "Thêm loại hàng thành công!");
        return "redirect:/admin/category/list";
    }

    @PostMapping("/{categoryId}")
    public String updateCategory(@PathVariable int categoryId, CategoryRequest request, RedirectAttributes redirectAttributes) {
        categoryService.updateCategory(categoryId, request);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
        return "redirect:/admin/category/list";
    }
}