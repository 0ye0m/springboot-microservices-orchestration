package org.inventoryservice.controller;

import jakarta.validation.Valid;
import org.inventoryservice.dto.*;
import org.inventoryservice.entity.Product;
import org.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Add Product
    @PostMapping("/products")
    public ProductResponse addProduct(
            @Valid @RequestBody ProductRequest request) {

        return inventoryService.addProduct(request);
    }

    // Get Product
    @GetMapping("/products/{productId}")
    public ProductResponse getProduct(
            @PathVariable Integer productId) {

        return inventoryService.getProduct(productId);
    }

    // Get All Products
    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return inventoryService.getAllProducts();
    }

    // Update Product
    @PutMapping("/products/{productId}")
    public ProductResponse updateProduct(
            @PathVariable Integer productId,
            @Valid @RequestBody ProductRequest request) {

        return inventoryService.updateProduct(
                productId,
                request
        );
    }

    // Delete Product
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(
            @PathVariable Integer productId) {

        return inventoryService.deleteProduct(productId);
    }

    // Check Inventory
    @GetMapping("/{productId}")
    public InventoryResponse checkInventory(
            @PathVariable Integer productId) {

        return inventoryService.checkInventory(productId);
    }

    // Reduce Stock
    @PutMapping("/reduce/{productId}")
    public ProductResponse reduceStock(
            @PathVariable Integer productId) {

        return inventoryService.reduceStock(productId);
    }
}