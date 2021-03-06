package com.shesternev.jpa.model;

import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

    @NotNull
    private String street;

    @NotNull
    @Column(length = 6)
    private String zipcode;

    @NotNull
    private String city;

    protected Address() {

    }

    public Address(String street, String zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }
}
