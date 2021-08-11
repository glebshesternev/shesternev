package com.shesternev.jpa.controller;

import com.shesternev.jpa.dto.ItemDto;
import com.shesternev.jpa.service.ItemService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/{id}")
    public ItemDto getItem(@PathVariable long id) {
        return itemService.getItemById(id);
    }

    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItemNameAndPrice(@PathVariable long id, @RequestBody @Valid ItemDto item) {
        itemService.updateItemNameAndPrice(id, item.getName(), item.getPrice());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody @Valid ItemDto itemDto,
                              BindingResult result,
                              HttpServletResponse response) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        itemService.addItem(itemDto);
        response.setHeader("Location", "/users/" + itemDto.getId());
        return itemDto;
    }
}
