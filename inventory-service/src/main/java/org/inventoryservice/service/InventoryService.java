package org.inventoryservice.service;

import jakarta.transaction.Transactional;
import org.inventoryservice.dto.*;
import org.inventoryservice.entity.Product;
import org.inventoryservice.exception.*;
import org.inventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    // Add Product
    public ProductResponse addProduct(
            ProductRequest request) {

        if(productRepository.existsById(
                request.getProductId())) {

            throw new ProductAlreadyExistsException(
                    "Product already exists"
            );
        }

        Product product = new Product();

        product.setProductId(request.getProductId());
        product.setProductName(request.getProductName());
        product.setQuantity(request.getQuantity());

        Product savedProduct =
                productRepository.save(product);

        return new ProductResponse(
                savedProduct.getProductId(),
                savedProduct.getProductName(),
                savedProduct.getQuantity()
        );
    }

    // Get Product
    public ProductResponse getProduct(Integer productId) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"
                                ));

        return new ProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getQuantity()
        );
    }

    // Get All Products
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    // Update Product
    public ProductResponse updateProduct(
            Integer productId,
            ProductRequest request) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"
                                ));

        product.setProductName(
                request.getProductName());

        product.setQuantity(
                request.getQuantity());

        Product updatedProduct =
                productRepository.save(product);

        return new ProductResponse(
                updatedProduct.getProductId(),
                updatedProduct.getProductName(),
                updatedProduct.getQuantity()
        );
    }

    // Delete Product
    public String deleteProduct(Integer productId) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"
                                ));

        productRepository.delete(product);

        return "Product deleted successfully";
    }

    // Check Inventory
    public InventoryResponse checkInventory(
            Integer productId) {

        Product product =
                productRepository.findById(productId)
                        .orElse(null);

        if(product == null) {

            return new InventoryResponse(
                    productId,
                    null,
                    false,
                    0
            );
        }

        boolean available =
                product.getQuantity() > 0;

        return new InventoryResponse(
                product.getProductId(),
                product.getProductName(),
                available,
                product.getQuantity()
        );
    }

    // Reduce Stock
    @Transactional
    public ProductResponse reduceStock(
            Integer productId) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"
                                ));

        if(product.getQuantity() <= 0) {

            throw new InsufficientStockException(
                    "Insufficient stock"
            );
        }

        product.setQuantity(
                product.getQuantity() - 1
        );

        Product updatedProduct =
                productRepository.save(product);

        return new ProductResponse(
                updatedProduct.getProductId(),
                updatedProduct.getProductName(),
                updatedProduct.getQuantity()
        );
    }
}