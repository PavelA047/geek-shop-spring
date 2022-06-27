package com.example.imageservice.services;

import contract.entities.ProductImage;

import java.util.List;

public interface ImageService {

    List<ProductImage> getImagesById(long id);
}
