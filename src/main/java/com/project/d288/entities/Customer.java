package com.project.d288.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @Column(name="customer_first_name")
    private String firstName;

    @Column(name="customer_last_name")
    private String lastName;

    @Column(name="address")
    private String address;

    @Column(name="postal_code")
    private String postal_code;

    @Column(name="phone")
    private String phone;

    @Column(name="create_date")
    private Date create_date;

    @Column(name="last_update")
    private Date lat_update;

    @ManyToOne
    @JoinColumn(name="division_id")
    private Division division;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Cart> carts;

    public void add(Cart cart) {
        if (cart != null) {
            if (carts == null) {
                carts = new HashSet<>();
            }
            carts.add(cart);
            cart.setCustomer(this);
        }
    }

    public Customer() {}
}
