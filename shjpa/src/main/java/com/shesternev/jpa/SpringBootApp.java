package com.shesternev.jpa;

import com.shesternev.jpa.model.Address;
import com.shesternev.jpa.model.BankAccount;
import com.shesternev.jpa.model.BillingDetails;
import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.model.Item;
import com.shesternev.jpa.model.User;
import com.shesternev.jpa.service.ICategoryService;
import com.shesternev.jpa.service.IItemService;
import com.shesternev.jpa.service.IUserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootApp implements CommandLineRunner {

    private final IUserService userService;
    private final IItemService itemService;
    private final ICategoryService categoryService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void run(String... args) {
        Address address = new Address("Smolenskaya", "196081", "Spb");
        User user = new User("Gleb", "Shesternev", address, address);
        Item item = new Item();
        item.setPrice(100);
        item.setImages(Set.of("foo.png", "bar.jpg"));
        item.setBuyer(user);
        BankAccount billingDetails = new BankAccount();
        billingDetails.setOwner("Shesternev Gleb");
        billingDetails.setAccount("230493480324");
        billingDetails.setBankName("Alfa-Bank");
        user.setBillingDetails(billingDetails);
        Category category = new Category();
        category.setName("Games");
        category.getItemAddedBy().put(item, user);
        userService.add(user);
        itemService.add(item);
        categoryService.add(category);
    }
}

