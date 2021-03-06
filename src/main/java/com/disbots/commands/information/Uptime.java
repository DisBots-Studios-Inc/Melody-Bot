package com.disbots.commands.information;

import com.disbots.core.Main;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Uptime implements MessageCreateListener
{
    @Override
    public void onMessageCreate(MessageCreateEvent message)
    {
        if (message.getMessageContent().equalsIgnoreCase(Main.Prefix + "uptime"))
        {
            EmbedBuilder UptimeEmbed = new EmbedBuilder()
                    .setTitle("Uptime")
                    .setDescription("The Bot has been online for " + CalculateEvalTime(Main.TimeWhenStarted) + " seconds")
                    .setColor(new Color(42, 179, 7))
                    .setFooter("", message.getMessageAuthor().getAvatar());
            message.getChannel().sendMessage(UptimeEmbed);
        }
    }

    private long CalculateEvalTime(Long StartedTime)
    {
        long currentTime = System.nanoTime();
        long ElapsedTime = currentTime - StartedTime;

        return TimeUnit.SECONDS.convert(ElapsedTime, TimeUnit.NANOSECONDS);
    }
}
