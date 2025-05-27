package com.project.d288.services;

import com.project.d288.dao.CartRepository;
import com.project.d288.dao.CustomerRepository;
import com.project.d288.entities.Cart;
import com.project.d288.entities.CartItem;
import com.project.d288.entities.Customer;
import com.project.d288.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CartRepository cartRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository,
                               CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Retrieve order info from data transfer object
        Cart cart = purchase.getCart();

//        // Validate the cart and its items
//        if (cart == null || cart.getCartItem() == null || cart.getCartItem().isEmpty()) {
//            return new PurchaseResponse("Error: The cart cannot be empty.");
//        }

        // Generate tracking number and assign it to the cart
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // Populate cart with cartItems
        Set<CartItem> cartItem = purchase.getCartItem();
        cartItem.forEach(item -> item.setCart(cart));
        cartItem.forEach(item -> cart.add(item));

        // Set status
        cart.setCustomer(purchase.getCustomer());
        cart.setStatus(StatusType.ordered);

        // Save cart with generated tracking number
        cartRepository.save(cart);

        //Populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);
        customerRepository.save(customer);

        // Return a response with the tracking number
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // Generate random UUID
        return UUID.randomUUID().toString();
    }

}

