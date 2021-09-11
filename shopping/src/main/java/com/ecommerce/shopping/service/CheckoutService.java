package com.ecommerce.shopping.service;

import com.ecommerce.shopping.dto.Purchase;
import com.ecommerce.shopping.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
