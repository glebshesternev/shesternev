package com.shesternev.jpa.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "USERS")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull //ignored when generating DDL
    @Column(nullable = false, length = 20) //not ignored when generating DDL
    private String firstName;

    @NotNull
    @Column(nullable = false, length = 20)
    private String lastName;

    private Address homeAddress;

    @AttributeOverride(name = "street", column = @Column(name = "SHIPPING_STREET"))
    @AttributeOverride(name = "zipcode", column = @Column(name = "SHIPPING_ZIPCODE", length = 6))
    @AttributeOverride(name = "city", column = @Column(name = "SHIPPING_CITY"))
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private BillingDetails billingDetails;

    @OneToMany(mappedBy = "buyer")
    @BatchSize(size = 10)
    private Set<Item> boughtItems = new HashSet<>();

    protected User() {

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
