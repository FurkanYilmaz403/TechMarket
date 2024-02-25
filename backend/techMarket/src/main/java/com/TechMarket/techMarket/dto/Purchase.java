package com.TechMarket.techMarket.dto;

import com.TechMarket.techMarket.entity.Address;
import com.TechMarket.techMarket.entity.Customer;
import com.TechMarket.techMarket.entity.Order;
import com.TechMarket.techMarket.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
