package com.disbots.util.logging;

import java.util.Calendar;
import java.util.Date;

public class Log
{
    /* Logging Class, where we store all our logger stuff. */

    public void log(LogTypes logTypes, String message, String LogSrc)
    {
        if (logTypes.equals(LogTypes.INFO))
        {
            System.out.println(this.getTime() + LogColors.GREEN + " INFO " + LogColors.RESET + "[" + this.getSrc(LogSrc) + "] " + message);
        }
        else if (logTypes.equals(LogTypes.WARNING))
        {
            System.out.println(this.getTime() + LogColors.YELLOW + " WARNING " + LogColors.RESET + "[" + this.getSrc(LogSrc) + "] " + message);
        }
    }

    public void log(LogTypes logTypes, String message, String LogSrc, Exception error)
    {
        if (logTypes.equals(LogTypes.INFO))
        {
            System.out.println(this.getTime() + LogColors.GREEN + " INFO " + LogColors.RESET + "[" + this.getSrc(LogSrc) + "] " + message);
        }
        else if (logTypes.equals(LogTypes.WARNING))
        {
            System.out.println(this.getTime() + LogColors.YELLOW + " WARNING " + LogColors.RESET + "[" + this.getSrc(LogSrc) + "] " + message);
        }
        else if (logTypes.equals(LogTypes.ERROR))
        {
            System.out.println(this.getTime() + LogColors.RED + " ERROR " + LogColors.RESET + "[" + this.getSrc(LogSrc) + "] " + message + "\n" + "> " + error);
        }
    }

    public String getSrc(String src)
    {
        if (src.isEmpty()) {
            return "OTHER";
        }

        return src.toUpperCase();
    }

    public String getTime()
    {
        int hours = Calendar.HOUR_OF_DAY;
        int minutes = Calendar.MINUTE;
        int seconds = Calendar.SECOND;

        return "[" + hours + ":" + minutes + ":" + seconds + "]";
    }
}