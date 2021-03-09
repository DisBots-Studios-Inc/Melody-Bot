package com.disbots.core;

import com.disbots.commands.information.BotInfo;
import com.disbots.commands.information.Ping;
import com.disbots.commands.system.Github;
import com.disbots.commands.system.Uptime;
import com.disbots.utilities.Log;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

import java.util.Arrays;

public class Main
{
    /* Getting the variables we need */
    public static final String Prefix = ";";
    private static final Log logger = new Log();

    public static void main(String[] args)
    {
        /* Logging stuff */
        logger.info("Starting bot...", "client");

        /* Making out Client and Updating it's status and Activity. */
        DiscordApi Bot = new DiscordApiBuilder().setToken(System.getenv().get("TOKEN")).setAllIntents().login().join();
        Bot.updateStatus(UserStatus.IDLE);
        Bot.updateActivity(ActivityType.LISTENING, "Melodies!");

        /* Forgoing Normal JavaCord commands for sdcf4j command handler and setting default prefix */
        CommandHandler handler = new JavacordHandler(Bot);
        handler.setDefaultPrefix(Prefix);

        logger.info("Loading resources...", "client");

        /* Registering All commands and Logging them. */
        handler.registerCommand(new Github());
        handler.registerCommand(new Ping());
        handler.registerCommand(new Uptime());
        handler.registerCommand(new BotInfo());

        logger.info("Loaded " + Arrays.stream(handler.getCommands().toArray()).count() + " commands!", "commands");
        logger.info("Loaded resources! Ready for operation!", "client");
    }
}
