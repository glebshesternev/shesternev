package com.shesternev.jpa.controller;

import com.shesternev.jpa.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GeneralController {

    private final GeneralService generalService;

    @PutMapping(value = "/items/{id}/buyer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItemBuyer(@PathVariable long id, @RequestParam long buyerId) {
        generalService.addItemBuyer(id, buyerId);
    }

    @PutMapping(value = "/categories/{id}/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItemCategory(@PathVariable long id, @RequestParam long userId, @RequestParam long itemId) {
        generalService.addItemToCategory(id, userId, itemId);
    }

}
