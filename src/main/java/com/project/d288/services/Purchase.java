package com.project.d288.services;

import com.project.d288.entities.Cart;
import com.project.d288.entities.CartItem;
import com.project.d288.entities.Customer;

import java.util.HashSet;
import java.util.Set;


public class Purchase {

    private Customer customer;

    private Cart cart;

   private Set<CartItem> cartItem = new HashSet<>();


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(Set<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}