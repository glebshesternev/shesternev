package com.shesternev.jpa.dto;

import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.model.Item;
import com.shesternev.jpa.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto {

    protected CategoryDto() {

    }

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
        itemAddedBy = convertMap(category.getItemAddedBy());
    }

    private long id;
    @NotNull
    private String name;
    private Map<String, ItemDto> itemAddedBy = new HashMap<>();

    private Map<String, ItemDto> convertMap(Map<User, Item> map) {
        HashMap<String, ItemDto> mapDto = new HashMap<>();
        map.forEach((key, value) -> mapDto.put(key.getId() + ": " + key.getFirstName() + " " + key.getLastName(),
                                               new ItemDto(value)));
        return mapDto;
    }

    public Category toCategory() {
        return new Category(name);
    }
}
