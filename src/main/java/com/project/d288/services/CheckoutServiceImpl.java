package com.project.d288.services;

import com.project.d288.dao.CartRepository;
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

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Retrieve order info from data transfer object
        Cart cart = purchase.getCart();

        // Generate tracking number and assign it to the cart
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // Populate cart with cartItems
        Set<CartItem> cartItem = purchase.getCartItems();
        cartItem.forEach(item -> item.setCart(cart));
        cartItem.forEach(item -> cart.add(item));

        // Validate the cart and its items
        if (cart.getCartItem() == null || cart.getCartItem().isEmpty()) {
            return new PurchaseResponse("Error: The cart cannot be empty.");
        }

        //validate party_size
        if (cart.getParty_size() <= 0) {
            return new PurchaseResponse("Error: Party size must be greater than 0.");
        }

        // Set status
        cart.setStatus(StatusType.ordered);

        // Save cart with generated tracking number
        cartRepository.save(cart);

        //Populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        // Return a response with the tracking number
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // Generate random UUID
        return UUID.randomUUID().toString();
    }

}

