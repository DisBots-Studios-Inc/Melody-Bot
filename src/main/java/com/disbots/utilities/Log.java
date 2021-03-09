package com.disbots.utilities;

public class Log
{
    /* Logging Class, where we store all our logger stuff. */

    public String getSrc(String src)
    {
        /* Get's the type of message. eg:- HANDLERS, CLIENT etc. */
        String srcType;
        if (src.isEmpty())
        {
            srcType = "OTHER";
        }
        else
        {
            srcType = src.toUpperCase();
        }
        return srcType;
    }

    public void info(String message, String src)
    {
        /* Logs info */

        System.out.println(LogColors.GREEN + "INFO " + LogColors.RESET + "[" + this.getSrc(src) + "] " + message);
    }

    public void warning(String message, String src)
    {
        /* Logs warnings */

        System.out.println(LogColors.YELLOW + "WARNING " + LogColors.RESET + "[" + this.getSrc(src) + "] " + message);
    }

    public void error(String message, String src, Exception error)
    {
        System.out.println(LogColors.RED + "ERROR " + LogColors.RESET + "[" + this.getSrc(src) + "] " + message + "\n" + "> " + error);
    }
}
