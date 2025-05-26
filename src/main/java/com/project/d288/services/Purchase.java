package com.project.d288.services;

import com.project.d288.entities.Cart;
import com.project.d288.entities.CartItem;
import com.project.d288.entities.Customer;
import com.project.d288.entities.Excursion;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Set<CartItem> cartItem = new HashSet<>();
    private Cart cart;
    private Set<Excursion> excursions = new HashSet<>();

}