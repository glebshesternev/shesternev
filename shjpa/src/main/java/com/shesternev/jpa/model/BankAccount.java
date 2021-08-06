package com.shesternev.jpa.model;

import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "BANK_ACCOUNTS")
public class BankAccount extends BillingDetails{

    @NotNull
    @Column(nullable = false)
    protected String account;

    @NotNull
    @Column(nullable = false)
    protected String bankName;

}
