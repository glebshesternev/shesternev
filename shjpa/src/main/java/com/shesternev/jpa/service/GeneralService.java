package com.shesternev.jpa.service;

public interface GeneralService {

    void addItemToCategory(long categoryId, long userId, long itemId);

    void addItemBuyer(long itemId, long userId);
}
