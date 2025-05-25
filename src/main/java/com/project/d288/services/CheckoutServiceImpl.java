package com.project.d288.services;

import com.project.d288.dao.CartItemRepository;
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
public class CheckoutServiceImpl implements CheckoutService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public CheckoutServiceImpl(CartRepository cartRepository,
                               CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //retrieve order info from data transfer object
        Cart cart = purchase.getCart();

        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        //populate cart with cartItems
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> item.setCart(cart));
        cartItems.forEach(item -> cart.add(item));

        //populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        //set status
        cart.setStatus(StatusType.ordered);

        //save
        cartRepository.save(cart);

        //return a response
        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {

            //generate random UUID
            return UUID.randomUUID().toString();

    }


}
