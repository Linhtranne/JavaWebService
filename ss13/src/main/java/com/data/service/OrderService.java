package com.data.service;

import com.data.model.dto.request.CheckoutRequest;

public interface OrderService {
    void checkout(CheckoutRequest request);
}