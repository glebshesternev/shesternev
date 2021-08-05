package com.shesternev.jpa.model;

import com.sun.istack.NotNull;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull  //ignored when generating DDL
    @Column(nullable = false, length = 20) //not ignored when generating DDL
    private String firstName;

    @NotNull
    @Column(nullable = false, length = 20)
    private String lastName;

    private Address homeAddress;

    @AttributeOverrides({
        @AttributeOverride(name = "street",
            column = @Column(name = "SHIPPING_STREET")),
        @AttributeOverride(name = "zipcode",
            column = @Column(name = "SHIPPING_ZIPCODE")),
        @AttributeOverride(name = "city",
            column = @Column(name = "SHIPPING_CITY"))
    })
    private Address shippingAddress;

    protected User() {

    }

    public User(String firstName, String lastName, Address homeAddress, Address shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.shippingAddress = shippingAddress;
    }

}
