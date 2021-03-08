package com.disbots.utilities;

public class Log {
    public String getSrc(String src) {
        String srcType;
        if (src.isEmpty()) {
            srcType = "OTHER";
        } else {
            srcType = src.toUpperCase();
        }
        return srcType;
    }

    public void info(String message, String src) {
        System.out.println(LogColors.GREEN + "INFO " + LogColors.RESET + "[" + this.getSrc(src) + "] " + message);
    }

    public void warning(String message, String src) {
        System.out.println(LogColors.YELLOW + "WARNING " + LogColors.RESET + "[" + this.getSrc(src) + "] " + message);
    }

    public void error(String message, String src) {
        System.out.println(LogColors.RED + "ERROR " + LogColors.RESET + "[" + this.getSrc(src) + "] " + message);
    }
}
