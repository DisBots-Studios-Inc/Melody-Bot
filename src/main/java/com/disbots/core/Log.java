package com.disbots.core;

import com.disbots.util.LogColors;
import com.disbots.util.LogTypes;

public class Log
{
    /* Logging Class, where we store all our logger stuff. */

    public void log(LogTypes logTypes, String message, String LogSrc)
    {
        if (logTypes.equals(LogTypes.INFO))
        {
            System.out.println(LogColors.GREEN + "INFO " + LogColors.RESET + "[" + LogSrc + "] " + message);
        }
        else if (logTypes.equals(LogTypes.WARNING))
        {
            System.out.println(LogColors.YELLOW + "WARNING " + LogColors.RESET + "[" + LogSrc + "] " + message);
        }
        else if (logTypes.equals(LogTypes.COMMANDS))
        {
            System.out.println(LogColors.CYAN + "COMMANDS" + LogColors.RESET + "[" + LogSrc + "] " + message);
        }
        else if (logTypes.equals(LogTypes.THREAD))
        {
            System.out.println(LogColors.BLUE + "THREAD" + LogColors.RESET + "[" + LogSrc + "] " + message);
        }
    }

    public void log(String message, LogTypes logTypes, String LogSrc, Exception error)
    {
        if (logTypes.equals(LogTypes.INFO))
        {
            System.out.println(LogColors.GREEN + "INFO " + LogColors.RESET + "[" + LogSrc + "] " + message);
        }
        else if (logTypes.equals(LogTypes.WARNING))
        {
            System.out.println(LogColors.YELLOW + "WARNING " + LogColors.RESET + "[" + LogSrc + "] " + message);
        }
        else if (logTypes.equals(LogTypes.ERROR))
        {
            System.out.println(LogColors.RED + "ERROR " + LogColors.RESET + "[" + LogSrc + "] " + message + "\n" + "> " + error);
        }
        else if (logTypes.equals(LogTypes.COMMANDS))
        {
            System.out.println(LogColors.CYAN + "COMMANDS" + LogColors.RESET + "[" + LogSrc + "] " + message);
        }
        else if (logTypes.equals(LogTypes.THREAD))
        {
            System.out.println(LogColors.BLUE + "THREAD" + LogColors.RESET + "[" + LogSrc + "] " + message);
        }
    }
}