package com.inventory_system.backend.service;

import com.inventory_system.backend.dto.ProductDTO;
import com.inventory_system.backend.entity.Product;
import com.inventory_system.backend.repo.ProductRepository;
import com.inventory_system.backend.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Cacheable("unordered_products")
    public List<Product> getByUnordered() throws RuntimeException{
        return this.productRepository.findAllByIsAddedFalse().orElseThrow(() ->
                new RuntimeException("Cant find the unordered products"));
    }

    @CacheEvict(value = "unordered_products", allEntries = true)
    public void createProduct(ProductDTO productDTO){
      this.productRepository.save(mapToProduct(productDTO));
    };


    public Product mapToProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setAdded(true);
        product.setCreatedOn(LocalDate.now());
        product.setUpdatedOn(LocalDate.now());
        product.setName(productDTO.getName());
        product.setImage("");
        product.setPrice(productDTO.getPrice());
        product.setQuality(productDTO.getQuality());
        return product;
    }

    public String updateProduct(){
        return "couldnt update product";
    }

    public String deleteProduct(){
        return "Couldnt delete product";
    }

}
