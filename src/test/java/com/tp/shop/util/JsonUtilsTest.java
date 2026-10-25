package com.tp.shop.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilsTest {

    @Test
    void nullBecomesEmptyString() {
        assertEquals("", JsonUtils.escape(null));
    }

    @Test
    void plainTextIsUnchanged() {
        assertEquals("Laptop Pro", JsonUtils.escape("Laptop Pro"));
    }

    @Test
    void specialCharactersAreEscaped() {
        assertEquals("a\\\"b\\\\c\\n\\t", JsonUtils.escape("a\"b\\c\n\t"));
    }

    @Test
    void controlCharactersUseUnicodeEscape() {
        assertEquals("\\u0001", JsonUtils.escape("\u0001"));
    }
}
