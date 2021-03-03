package com.disbots.core;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import com.disbots.commands.Ping;
import java.util.Arrays;
import java.lang.String;

public class Main
{
    public static final String Prefix = ".";

    public static void main(String[] args)
    {
        System.out.println("Starting Bot....");

        DiscordApi Bot = new DiscordApiBuilder().setToken(System.getenv().get("TOKEN")).login().join();

        System.out.println("Bot Online, Loading resources");

        Bot.addListener(new Ping());

        System.out.println("Loading listeners: " + Arrays.stream(Bot.getListeners().values().toArray()).count());
        System.out.println("Loaded listeners");
        System.out.println("Loading resources...");
        System.out.println("Loaded resources! Ready for operation!");
    }
}
