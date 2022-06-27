package com.geekbrains.geekmarketwinter.services;

import contract.entities.ProductImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "image-service-client", contextId = "image-service")
public interface ImageService {

    @GetMapping("/image/getImagesById")
    List<ProductImage> getImagesById(@RequestParam long id);
}
