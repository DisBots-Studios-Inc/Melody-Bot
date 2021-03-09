package com.disbots.util;

import java.awt.*;

public enum EmbedColors
{
    /* Embed Color Pallet for the bot. */

    SUCCESS(new Color(46,204,113)),
    NEUTRAL(new Color(255, 86, 87)),
    WARNING(new Color(241, 196, 15)),
    ERROR(new Color(231, 76, 60));

    private final Color code;

    EmbedColors(Color code)
    {
        this.code = code;
    }

    public Color getCode()
    {
        return code;
    }
}
