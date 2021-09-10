package com.ecommerce.shopping.dto;

import com.ecommerce.shopping.model.Address;
import com.ecommerce.shopping.model.Customer;
import com.ecommerce.shopping.model.Order;
import com.ecommerce.shopping.model.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
