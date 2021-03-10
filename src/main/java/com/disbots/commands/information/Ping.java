package com.disbots.commands.information;

import com.disbots.util.EmbedColors;
import com.disbots.core.Log;
import com.disbots.util.LogTypes;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Ping implements CommandExecutor
{


    @Command(aliases = {"ping", "p"}, description = "Displays network information like Latency.", usage = "ping", showInHelpPage = false)
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
            new Log().log("Error while evaluating latency in a guild", LogTypes.ERROR, "Ping_Command", e);
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

            EmbedBuilder PingEmbed = null;
            try
            {
                PingEmbed = new EmbedBuilder()
                        .setTitle(":ping_pong: Pong!")
                        .setDescription(
                                "Evaluation Time: " + "**"+ GetEvalTime() +"**" + "ms\n" +
                                "Bot Latency: " + "**"+GatewayLatency+"**" + "ms\n" +
                                "Rest latency: " + "**"+Time.toMillis()+"**" + "ms\n")
                        .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                        .setColor(EmbedColors.NEUTRAL.getCode());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            EmbedBuilder finalPingEmbed = PingEmbed;
            message.getChannel().sendMessage(InitialPing).thenAccept(MessageToBeEdited -> MessageToBeEdited.getApi().getThreadPool().getScheduler().schedule(() -> {
                MessageToBeEdited.edit(finalPingEmbed);
            }, 1, TimeUnit.SECONDS));
        });
    }

    private Long GetEvalTime() throws InterruptedException
    {
        Log logger = new Log();

        logger.log(LogTypes.INFO, "Getting evaluation time...", "Ping_Command");

        /*Getting evaluating Time in seconds.*/

        //Code being being measured starts
        Instant start = Instant.now();

        //sleep for 5 seconds (measure time period)
        TimeUnit.SECONDS.sleep(5);

        //Code being measured ends
        Instant end = Instant.now();

        //Interval during 5 seconds of execution aka eval time
        Duration interval = Duration.between(start, end);

        logger.log(LogTypes.INFO, "Evaluation time calculation successful!", "Ping_Command");

        //returning eval time
        return interval.getSeconds();
    }
}
