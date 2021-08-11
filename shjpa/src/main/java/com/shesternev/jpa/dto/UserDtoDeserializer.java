package com.shesternev.jpa.dto;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

public class UserDtoDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(String s, DeserializationContext deserializationContext) {
        return null;
    }
}
