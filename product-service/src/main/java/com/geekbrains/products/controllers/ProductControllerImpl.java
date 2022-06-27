package com.geekbrains.products.controllers;

import com.geekbrains.products.services.ProductService;
import contract.entities.Product;
import contract.entities.ProductDTO;
import contract.entities.RestPageImpl;
import contract.specifications.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductControllerImpl implements ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

    @Override
    public Product getProductByTitle(String title) {
        return productService.getProductByTitle(title);
    }

    @Override
    public RestPageImpl getProductsWithPagingAndFiltering(int page, int pageSize, String word, Double min, Double max) {
        Specification<Product> spec = Specification.where(null);
        if (word != null) {
            spec = spec.and(ProductSpecs.titleContains(word));
        }
        if (min != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(min));
        }
        if (max != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEq(max));
        }
        Page<Product> products = productService.getProductsWithPagingAndFiltering(page, pageSize, spec);
        return new RestPageImpl(products.toList(), products.getNumber(), pageSize, products.getTotalElements());
    }

    @Override
    public void saveProduct(ProductDTO product) {
        productService.saveProduct(product);
    }

    @Override
    public void addImage(String title, String imageName) {
        productService.addImage(productService.getProductByTitle(title).getId(), imageName);
    }

    @Override
    public void deleteProduct(long id) {
        productService.deleteProduct(id);
    }

    @Override
    public ProductDTO getProductDtoById(Long id) {
        Product productEntity = productService.getProductById(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setCategoryId(productEntity.getCategory().getId());
        productDTO.setVendorCode(productEntity.getVendorCode());
        productDTO.setTitle(productEntity.getTitle());
        productDTO.setShortDescription(productEntity.getShortDescription());
        productDTO.setFullDescription(productEntity.getFullDescription());
        productDTO.setPrice(productEntity.getPrice());
        return productDTO;
    }
}
