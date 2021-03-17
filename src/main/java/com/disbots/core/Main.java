package com.disbots.core;

import com.disbots.commands.help.Help;
import com.disbots.commands.information.BotInfo;
import com.disbots.commands.information.Ping;
import com.disbots.commands.music.Play;
import com.disbots.commands.music.Stop;
import com.disbots.commands.system.Github;
import com.disbots.commands.system.Uptime;
import com.disbots.util.logging.Log;
import com.disbots.util.logging.LogTypes;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

import java.util.Arrays;

public class Main extends Thread
{
    public static final String Prefix = ";";
    public static Log logger = new Log();

    public static void main(String[] args) throws InterruptedException
    {
        /* Logging stuff */
        logger.log(LogTypes.INFO, "Starting bot...", "client");

        /* Making out Client and Updating it's status and Activity. */

        DiscordApi Bot = new DiscordApiBuilder().setToken(System.getenv().get("TOKEN")).setAllIntents().login().join();

        Bot.updateStatus(UserStatus.IDLE);
        Bot.updateActivity(ActivityType.LISTENING, "Melodies!");

        /* Forgoing Normal JavaCord commands for SDCF4J command handler and setting default prefix */
        CommandHandler handler = new JavacordHandler(Bot);
        handler.setDefaultPrefix(Prefix);

        logger.log(LogTypes.INFO, "Loading resources...", "client");
        logger.log(LogTypes.INFO, "Loading commands...", "client");

        /* Registering All commands and Logging them. */
        handler.registerCommand(new Help(handler));
        handler.registerCommand(new Github());
        handler.registerCommand(new Ping());
        handler.registerCommand(new Uptime());
        handler.registerCommand(new BotInfo());
        handler.registerCommand(new Play());
        handler.registerCommand(new Stop());

        logger.log(LogTypes.INFO, "Loaded " + Arrays.stream(handler.getCommands().toArray()).count() + " commands!", "client");
        logger.log(LogTypes.INFO, "Loaded resources! Ready for operation!", "client");

    }
}
