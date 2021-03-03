package com.disbots.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import com.disbots.core.Main;

public class Ping implements MessageCreateListener
{
    @Override
    public void onMessageCreate(MessageCreateEvent message) 
    {
        if (message.getMessageContent().equalsIgnoreCase(Main.Prefix + "ping"))
        {
            message.getChannel().sendMessage("Pong!");
        }
    }
}
