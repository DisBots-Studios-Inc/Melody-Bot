package com.disbots.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YoutubeApiServiceTest
{

    @Test
    @DisplayName("Check if APPLICATION_NAME is valid")
    void CheckIfGetServiceIsValid()
    {
        assertEquals("Melody-Bot", YoutubeApiService.APPLICATION_NAME);
    }
}