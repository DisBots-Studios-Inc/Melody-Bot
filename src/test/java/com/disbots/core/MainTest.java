package com.disbots.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
    @Test
    @DisplayName("Check Prefix")
    void CheckPrefix()
    {
        String expectedPrefix = ";";
        assertEquals(expectedPrefix, Main.Prefix);
    }
}