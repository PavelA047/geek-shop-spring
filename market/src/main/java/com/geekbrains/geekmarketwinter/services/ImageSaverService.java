package com.geekbrains.geekmarketwinter.services;

import contract.entities.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImageSaverService {
    private static final String UPLOADED_FOLDER = "./images/";

    private ProductService productService;
    private ImageService imageService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

        try {
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public void deleteFile(long productId) {
        List<ProductImage> productImageList = imageService.getImagesById(productId);
        for (ProductImage productImage : productImageList) {
            if (!productImage.getPath().isEmpty()) {
                try {
                    Files.delete(Paths.get(UPLOADED_FOLDER + productImage.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
