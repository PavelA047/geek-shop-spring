package com.example.imageservice.controllers;

import com.example.imageservice.services.ImageService;
import contract.entities.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageControllerImpl implements ImageController {
    private ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public List<ProductImage> getImagesById(long id) {
        return imageService.getImagesById(id);
    }
}
