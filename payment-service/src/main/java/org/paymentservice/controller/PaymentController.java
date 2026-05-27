package org.paymentservice.controller;


import org.paymentservice.dto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping
    public PaymentResponse makePayment(@RequestBody PaymentRequest request) {

        if(request.getAmount() > 0) {
            return new PaymentResponse("SUCCESS");
        }

        return new PaymentResponse("FAILED");
    }
}