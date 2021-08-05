package com.shesternev.jpa.model;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CREDIT_CARDS")
public class CreditCard extends BillingDetails{

    @NotNull
    @Column(nullable = false)
    protected String cardNumber;

    @NotNull
    @Column(nullable = false)
    protected String expMonth;

    @NotNull
    @Column(nullable = false)
    protected String expYear;

}
