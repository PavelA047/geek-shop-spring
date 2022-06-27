package com.example.imageservice.controllers;

import contract.entities.ProductImage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ImageController {

    @GetMapping("/image/getImagesById")
    List<ProductImage> getImagesById(@RequestParam long id);
}
