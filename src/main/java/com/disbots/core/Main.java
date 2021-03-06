package com.disbots.core;

import com.disbots.commands.help.BotInfo;
import com.disbots.commands.help.Help;
import com.disbots.commands.information.Uptime;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import com.disbots.commands.information.Ping;
import org.javacord.api.entity.activity.ActivityType;

import java.util.Arrays;
import java.lang.String;

public class Main
{
    public static final String Prefix = ";";
    public static long TimeWhenStarted = System.nanoTime();

    public static void main(String[] args)
    {
        System.out.println("Starting Bot....");

        DiscordApi Bot = new DiscordApiBuilder().setToken(System.getenv().get("TOKEN")).setAllIntents().login().join();
        Bot.updateActivity(ActivityType.LISTENING, "Melodies!");

        System.out.println("Bot Online, Loading resources");

        Bot.addListener(new Ping());
        Bot.addListener(new BotInfo());
        Bot.addListener(new Help());
        Bot.addListener(new Uptime());

        System.out.println("Loading listeners: " + Arrays.stream(Bot.getListeners().values().toArray()).count());
        System.out.println("Loaded listeners");
        System.out.println("Loading resources...");
        System.out.println("Loaded resources! Ready for operation!");
    }
}
