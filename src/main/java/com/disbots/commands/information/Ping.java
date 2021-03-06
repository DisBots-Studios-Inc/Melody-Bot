package com.disbots.commands.information;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import com.disbots.core.Main;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Ping implements MessageCreateListener
{
    @Override
    public void onMessageCreate(MessageCreateEvent message) 
    {
        if (message.getMessageContent().equalsIgnoreCase(Main.Prefix + "ping"))
        {
            try
            {
                SendEmbed(message);
            }
            catch (InterruptedException e)
            {
                EmbedBuilder ErrorEmbed = new EmbedBuilder()
                        .setTitle("Fatal Error!")
                        .setDescription("Fatal Error, While calculating Eval Time! Please contact Disbots inc.")
                        .setFooter("", message.getMessageAuthor().getAvatar())
                        .setColor(new Color(255,0,0));
                message.getChannel().sendMessage(ErrorEmbed);
                e.printStackTrace();
            }
        }
    }

    private Long GetEvalTime() throws InterruptedException
    {
        //Code being being measured starts
        Instant start = Instant.now();

        //sleep for 5 seconds (measure time period)
        TimeUnit.SECONDS.sleep(5);

        //Code being measured ends
        Instant end = Instant.now();

        //Interval during 5 seconds of execution aka eval time
        Duration interval = Duration.between(start, end);

        //returning eval time
        return interval.getSeconds();
    }

    private void SendEmbed(MessageCreateEvent message) throws InterruptedException
    {
        Long EvalTime = GetEvalTime();

        Color InitialPingEmbedColor = new Color(255, 121, 44);
        Color PingEmbedColor = new Color(42, 179, 7);
        long GatewayLatency = message.getApi().getLatestGatewayLatency().toMillis();
        CompletableFuture<Void> RESTLatency = message.getApi().measureRestLatency().thenAccept(Time -> {
            EmbedBuilder InitialPing = new EmbedBuilder()
                    .setTitle("**Testing Ping... :ping_pong:**")
                    .setColor(InitialPingEmbedColor)
                    .setFooter("", message.getMessageAuthor().getAvatar());

            EmbedBuilder PingEmbed = new EmbedBuilder()
                    .setTitle("**Latency of the bot:** ")
                    .setDescription("This says the current latency of the bot:\n" +
                            "**Evaluation Time** - " + EvalTime + "ms\n" +
                            "**Bot Latency** - " + GatewayLatency + "ms\n" +
                            "**Rest latency** - " + Time.toMillis() + "ms\n")
                    .setFooter("Please report if more than 1000ms", message.getMessageAuthor().getAvatar())
                    .setColor(PingEmbedColor);

            message.getChannel().sendMessage(InitialPing).thenAccept(MessageToBeEdited -> MessageToBeEdited.getApi().getThreadPool().getScheduler().schedule(() -> {
                MessageToBeEdited.edit(PingEmbed);
            }, 2, TimeUnit.SECONDS));
        });
    }
}
