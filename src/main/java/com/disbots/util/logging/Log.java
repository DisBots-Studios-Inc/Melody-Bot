package com.disbots.util.logging;

import javax.annotation.Nonnull;
import java.util.Date;

public class Log
{
    /**
     * Logs general warnings and info.
     *
     * @param logTypes The enum which contains the log types, used to identify the type
     * of log. It cannot be null
     * @param message the message to be displayed in the info or warning. It cannot be null.
     * @param LogSrc tells, where the log message is coming from.
     */
    public void log(@Nonnull LogTypes logTypes, @Nonnull String message, String LogSrc)
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

    /**
     * Logs errors and exceptions.
     *
     * @param logTypes The enum which contains the log types, used to identify the type
     * of log. It cannot be null
     * @param message The message to be displayed in the info or warning. It cannot be null.
     * @param LogSrc Tells, where the log message is coming from.
     * @param error The error which it should display on the console
     */
    public void log(@Nonnull LogTypes logTypes, @Nonnull String message, String LogSrc, @Nonnull Exception error)
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
            System.out.println(this.getTime() + LogColors.RED + " ERROR " + LogColors.RESET + "[" + this.getSrc(LogSrc) + "] " + message + "\n" + "> " + error.toString());
        }
    }

    /**
     * Logs errors and throwable.
     *
     * @param logTypes The enum which contains the log types, used to identify the type
     * of log. It cannot be null
     * @param message The message to be displayed in the info or warning. It cannot be null.
     * @param LogSrc Tells, where the log message is coming from.
     * @param error The throwable which it should display on the console
     */
    public void log(@Nonnull LogTypes logTypes, @Nonnull String message, String LogSrc, @Nonnull Throwable error)
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
            System.out.println(this.getTime() + LogColors.RED + " ERROR " + LogColors.RESET + "[" + this.getSrc(LogSrc) + "] " + message + "\n" + "> " + error.toString());
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
        Date dateNow = new Date();
        int hours = dateNow.getHours();
        int minutes = dateNow.getMinutes();
        int seconds = dateNow.getSeconds();

        return "[" + hours + ":" + minutes + ":" + seconds + "]";
    }
}