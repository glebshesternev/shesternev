package com.shesternev.jpa.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
        itemAddedBy = convertMapToDto(category.getItemAddedBy());
    }

    private long id;
    @NotNull
    private String name;
    @JsonDeserialize(keyUsing = UserDtoDeserializer.class)
    private Map<UserDto, ItemDto> itemAddedBy;

    private Map<UserDto, ItemDto> convertMapToDto(Map<User, Item> map) {
        Map<UserDto, ItemDto> mapDto = new HashMap<>();
        if (map != null)
            map.forEach((key, value) -> mapDto.put(new UserDto(key), new ItemDto(value)));
        return mapDto;
    }

    private Map<User, Item> convertMapFromDto(Map<UserDto, ItemDto> mapDto) {
        Map<User, Item> map = new HashMap<>();
        if (mapDto != null) {
            mapDto.forEach((key, value) -> map.put(key.toUser(), value.toItem()));
        }
        return map;
    }

    public Category toCategory() {
        Category category = new Category(name);
        category.setId(id);
        category.setItemAddedBy(convertMapFromDto(itemAddedBy));
        return category;
    }
}
