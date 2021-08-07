package com.shesternev.jpa.controller;

import com.shesternev.jpa.dto.CategoryDto;
import com.shesternev.jpa.model.Category;
import com.shesternev.jpa.service.CategoryService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public @ResponseBody
    CategoryDto getCategory(@PathVariable long id) {
        return categoryService.convertCategoryToDto(categoryService.getCategoryById(id));
    }

    @GetMapping
    public @ResponseBody
    List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories()
                              .stream()
                              .map(categoryService::convertCategoryToDto)
                              .toList();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategoryName(@PathVariable long id, @RequestBody @Valid CategoryDto category) {
        categoryService.updateCategoryName(id, category.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    CategoryDto createCategory(@RequestBody @Valid CategoryDto categoryDto,
                               BindingResult result,
                               HttpServletResponse response) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        Category category = categoryDto.toCategory();
        categoryService.addCategory(category);
        response.setHeader("Location", "/users/" + category.getId());
        return categoryService.convertCategoryToDto(category);
    }
}