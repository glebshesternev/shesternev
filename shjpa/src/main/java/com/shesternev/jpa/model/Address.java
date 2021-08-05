package com.shesternev.jpa.model;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false, length = 6)
    private String zipcode;

    @NotNull
    @Column(nullable = false)
    private String city;

    protected Address() {

    }

    public Address(String street, String zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }
}
