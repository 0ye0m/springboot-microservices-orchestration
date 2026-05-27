package org.inventoryservice.controller;

import org.inventoryservice.dto.InventoryResponse;
import org.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public InventoryResponse checkInventory(
            @PathVariable Integer productId) {

        return inventoryService.checkInventory(productId);
    }
}