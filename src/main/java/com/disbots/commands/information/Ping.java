package com.disbots.commands.information;

import com.disbots.util.EmbedColors;
import com.disbots.util.logging.Log;
import com.disbots.util.logging.LogTypes;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Ping implements CommandExecutor
{

    private final static Log logger = new Log();

    @Command(aliases = {"ping", "p"}, description = "Displays network information like Latency.", usage = "ping")
    public void OnPingCommand(MessageCreateEvent message)
    {
        /* Sending The embed and checking for errors in calculating the latency. */
        try
        {
            SendEmbed(message);
        }
        catch (InterruptedException e)
        {
            EmbedBuilder ErrorEmbed = new EmbedBuilder()
                    .setDescription("There was an error evaluating the latency! Please contact DisBots Inc.")
                    .setFooter("", message.getMessageAuthor().getAvatar())
                    .setColor(EmbedColors.ERROR.getCode());
            message.getChannel().sendMessage(ErrorEmbed);
            logger.log(LogTypes.ERROR, "Error while evaluating latency in a guild", "Ping_Command", e);
        }
    }

    private void SendEmbed(MessageCreateEvent message) throws InterruptedException
    {
        /* Sending and editing the embed. */

        long GatewayLatency = message.getApi().getLatestGatewayLatency().toMillis();
        CompletableFuture<Void> RESTLatency = message.getApi().measureRestLatency().thenAccept(Time -> {
            EmbedBuilder InitialPing = new EmbedBuilder()
                    .setDescription(":ping_pong: Testing Ping...")
                    .setColor(EmbedColors.NEUTRAL.getCode())
                    .setFooter("", message.getMessageAuthor().getAvatar());

            EmbedBuilder PingEmbed;
            PingEmbed = new EmbedBuilder()
                .setTitle(":ping_pong: Pong!")
                .setDescription(
                        "Bot Latency: " + "**"+GatewayLatency+"**" + "ms\n" +
                        "Rest latency: " + "**"+Time.toMillis()+"**" + "ms\n")
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                .setColor(EmbedColors.NEUTRAL.getCode());

            EmbedBuilder finalPingEmbed = PingEmbed;
            message.getChannel().sendMessage(InitialPing).thenAccept(MessageToBeEdited -> MessageToBeEdited.getApi().getThreadPool().getScheduler().schedule(() -> {
                MessageToBeEdited.edit(finalPingEmbed);
            }, 1, TimeUnit.SECONDS));
        });
    }
}
