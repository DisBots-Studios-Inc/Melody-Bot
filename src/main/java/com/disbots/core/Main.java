package com.disbots.core;

import com.disbots.commands.information.BotInfo;
import com.disbots.commands.help.Help;
import com.disbots.commands.system.Github;
import com.disbots.commands.system.Uptime;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import com.disbots.commands.information.Ping;
import org.javacord.api.entity.activity.ActivityType;
import com.disbots.utilities.Log;
import org.javacord.api.entity.user.UserStatus;

import java.util.Arrays;
import java.lang.String;
import java.util.Date;

public class Main
{
    public static final String Prefix = ";";
    public static long TimeWhenStarted = System.nanoTime();
    private final static Log logger = new Log();

    public static void main(String[] args)
    {
        logger.info("Starting bot...", "client");

        DiscordApi Bot = new DiscordApiBuilder().setToken(System.getenv().get("TOKEN")).setAllIntents().login().join();
        Bot.updateStatus(UserStatus.ONLINE);
        Bot.updateActivity(ActivityType.LISTENING, "Melodies!");

        logger.info("Loading resources...", "client");

        Bot.addListener(new Ping());
        Bot.addListener(new BotInfo());
        Bot.addListener(new Help());
        Bot.addListener(new Uptime());
        Bot.addListener(new Github());

        logger.info("Loaded " + Arrays.stream(Bot.getListeners().values().toArray()).count() + " listeners!", "listeners");
        logger.info("Loaded resources! Ready for operation!", "client");
    }
}
