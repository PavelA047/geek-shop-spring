package com.example.imageservice.services;

import com.example.imageservice.repositories.ImageRepository;
import contract.entities.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ProductImage> getImagesById(long id) {
//        return imageRepository.findAllById(Collections.singleton(id));

        List<ProductImage> all = imageRepository.findAll();
        List<ProductImage> returnedList = new ArrayList<>();
        for (ProductImage productImage : all) {
            if (productImage.getProduct().getId().equals(id)) returnedList.add(productImage);
        }
        return returnedList;
    }
}
