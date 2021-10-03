package com.ecommerce.shopping.service;

import com.ecommerce.shopping.dto.Purchase;
import com.ecommerce.shopping.dto.PurchaseResponse;
import com.ecommerce.shopping.model.Customer;
import com.ecommerce.shopping.model.Order;
import com.ecommerce.shopping.model.OrderItem;
import com.ecommerce.shopping.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //retrieve order from dto
        Order order = purchase.getOrder();
        //generate order id
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order :: add);
        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        //populate with customer with order
        Customer customer = purchase.getCustomer();
        //check if customer is an existing one
        String email = customer.getEmail();
        Customer existingCustomer = customerRepository.findByEmail(email);
        //if found assign order to that customer
        if(existingCustomer != null){
            customer = existingCustomer;
        }
        customer.add(order);
        //save to the database
        customerRepository.save(customer);
        PurchaseResponse purchaseResponse = new PurchaseResponse(orderTrackingNumber);
        return purchaseResponse;
    }

    private String generateOrderTrackingNumber() {
        //generate a random UUID number (UUID version-4)
        //For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        return UUID.randomUUID().toString();
    }
}
