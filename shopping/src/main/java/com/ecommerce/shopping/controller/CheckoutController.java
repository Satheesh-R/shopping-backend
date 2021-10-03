package com.ecommerce.shopping.controller;

import com.ecommerce.shopping.dto.Purchase;
import com.ecommerce.shopping.dto.PurchaseResponse;
import com.ecommerce.shopping.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        return checkoutService.placeOrder(purchase);
    }
}
