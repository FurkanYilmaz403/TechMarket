package com.TechMarket.techMarket.service;

import com.TechMarket.techMarket.dto.Purchase;
import com.TechMarket.techMarket.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
