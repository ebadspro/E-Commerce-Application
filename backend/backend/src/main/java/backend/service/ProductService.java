package backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.Products;
import backend.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productsRepository;

    public ProductService(ProductRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products createProducts(Products product) {
        return productsRepository.save(product);
    }

    public Products getProductByName(String name) {
        return productsRepository.findByName(name).
        orElseThrow(() -> new RuntimeException("Product not found for name: " + name));
    }

    public Products getProductById(Long id) {
        return productsRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
    }


}