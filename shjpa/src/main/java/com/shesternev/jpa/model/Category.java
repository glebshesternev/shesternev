package com.shesternev.jpa.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @ManyToMany
    @MapKeyJoinColumn(name = "USER_ID")
    @JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private Map<User, Item> itemAddedBy = new HashMap<>();

    protected Category() {

    }

    public Category(String name) {
        this.name = name;
    }
}

