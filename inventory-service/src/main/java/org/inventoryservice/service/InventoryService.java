package org.inventoryservice.service;

import org.inventoryservice.dto.InventoryResponse;
import org.inventoryservice.entity.Product;
import org.inventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    public InventoryResponse checkInventory(Integer productId) {

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
}