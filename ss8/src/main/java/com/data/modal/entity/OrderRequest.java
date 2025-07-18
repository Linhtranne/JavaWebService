package com.data.modal.entity;

import com.data.modal.entity.Order;
import com.data.modal.entity.OrderDetail;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Order order;
    private List<OrderDetail> orderDetails;
}