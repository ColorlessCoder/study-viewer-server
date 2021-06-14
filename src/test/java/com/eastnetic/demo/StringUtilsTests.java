package com.eastnetic.demo;

import com.eastnetic.demo.util.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringUtilsTests {

    @Test
    @DisplayName("isNullOrEmpty function should work")
    public void testIsNullOrEmpty() {
        Assertions.assertTrue(StringUtils.isNullOrEmpty(null), "should return true for null");
        Assertions.assertTrue(StringUtils.isNullOrEmpty(""), "should return true for empty string");
        Assertions.assertFalse(StringUtils.isNullOrEmpty("ABC"), "should return false for non-empty string");
    }

    @Test
    @DisplayName("nullSafeTrim function should work")
    public void testNullSafeTrim() {
        Assertions.assertNull(StringUtils.nullSafeTrim(null), "should return null for null");
        Assertions.assertEquals("ABC", StringUtils.nullSafeTrim("   ABC    "), "should trim whitespace from both side");
        Assertions.assertEquals("", StringUtils.nullSafeTrim("       "), "should return empty string if argument string only has whitespaces");
    }
}
