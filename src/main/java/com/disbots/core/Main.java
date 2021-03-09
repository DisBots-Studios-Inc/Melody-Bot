package com.disbots.core;

import com.disbots.commands.help.Help;
import com.disbots.commands.information.BotInfo;
import com.disbots.commands.information.Ping;
import com.disbots.commands.system.Github;
import com.disbots.commands.system.Uptime;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

import java.util.Arrays;

public class Main extends Thread
{
    public static void main(String[] args) throws InterruptedException
    {
        DiscordApi Bot = new DiscordApiBuilder().setToken(System.getenv().get("TOKEN")).setAllIntents().login().join();
        final String Prefix = ";";

        Thread BotCreateThread = new Thread(){
            /* Getting the variables we need */
            private final Log logger = new Log();

            public void run()
            {
                /* Logging stuff */
                logger.info("Starting bot...", "client");

                /* Making out Client and Updating it's status and Activity. */

                Bot.updateStatus(UserStatus.IDLE);
                Bot.updateActivity(ActivityType.LISTENING, "Melodies!");

            }

            public void start()
            {
                logger.info("Starting bot creation Thread", "cpuThreads");
                super.start();
            }
        };

        Thread LoadCommandsThread = new Thread(){
            final Log logger = new Log();

            public void run()
            {
                /* Forgoing Normal JavaCord commands for sdcf4j command handler and setting default prefix */
                CommandHandler handler = new JavacordHandler(Bot);
                handler.setDefaultPrefix(Prefix);

                logger.info("Loading resources...", "client");

                /* Registering All commands and Logging them. */
                handler.registerCommand(new Help(handler));
                handler.registerCommand(new Github());
                handler.registerCommand(new Ping());
                handler.registerCommand(new Uptime());
                handler.registerCommand(new BotInfo());

                logger.info("Loaded " + Arrays.stream(handler.getCommands().toArray()).count() + " commands!", "commands");
                logger.info("Loaded resources! Ready for operation!", "client");
            }

            public void start()
            {
                logger.info("Starting Loading Listeners Thread", "cpuThreads");
                super.start();
            }
        };

        BotCreateThread.setName("Melody - BotConnection");
        BotCreateThread.start();
        BotCreateThread.join();

        LoadCommandsThread.setName("Melody - LoadCommands");
        LoadCommandsThread.start();
    }
}
