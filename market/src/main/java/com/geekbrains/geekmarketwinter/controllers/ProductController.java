package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.services.CategoryService;
import com.geekbrains.geekmarketwinter.services.ImageSaverService;
import contract.entities.Product;
import contract.entities.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.geekbrains.geekmarketwinter.services.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageSaverService imageSaverService;

    @GetMapping("/add")
    public String processAddProduct(Model model) {
        model.addAttribute("newProduct", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-product-form";
    }

    @PostMapping("/add")
    public String processAddProduct(Model model,
                                    @Valid @ModelAttribute("newProduct") ProductDTO newProduct,
                                    BindingResult theBindingResult,
                                    @RequestParam("file") MultipartFile file) {
        String newProductTitle = newProduct.getTitle();
        logger.debug("Processing addProduct form for: " + newProductTitle);
        if (theBindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "add-product-form";
        }

        if (newProduct.getId() != null) {
            productService.saveProduct(newProduct);

            String fileName = imageSaverService.saveFile(file);
            productService.addImage(newProduct.getTitle(), fileName);

            model.addAttribute("addProductSuccess", "Product successfully added");
            model.addAttribute("newProduct", new ProductDTO());
            model.addAttribute("categories", categoryService.getAllCategories());
            logger.info("Successfully added product: " + newProductTitle);
            return "redirect:/shop";
        }

        Product existing = productService.getProductByTitle(newProductTitle);
        if (existing != null) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("addProductError", "Product with current title already exists.");
            model.addAttribute("categories", categoryService.getAllCategories());
            logger.debug("Product with current title already exists.");
            return "add-product-form";
        }

        productService.saveProduct(newProduct);

        String fileName = imageSaverService.saveFile(file);
        productService.addImage(newProduct.getTitle(), fileName);

        model.addAttribute("addProductSuccess", "Product successfully added");
        model.addAttribute("newProduct", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategories());
        logger.info("Successfully added product: " + newProductTitle);
        return "redirect:/shop";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("newProduct", productService.getProductDtoById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-product-form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        imageSaverService.deleteFile(id);
        productService.deleteProduct(id);
        return "redirect:/shop";
    }
}
