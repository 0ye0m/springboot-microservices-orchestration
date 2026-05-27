package org.inventoryservice.controller;


import org.inventoryservice.dto.InventoryResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @GetMapping("/{productId}")
    public InventoryResponse checkInventory(@PathVariable int productId) {


        if(productId == 1) {
            return new InventoryResponse(true);
        }

        return new InventoryResponse(false);
    }
}