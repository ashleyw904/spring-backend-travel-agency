package com.project.d288.services;

import com.project.d288.entities.Cart;
import com.project.d288.entities.CartItem;
import com.project.d288.entities.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;
}
