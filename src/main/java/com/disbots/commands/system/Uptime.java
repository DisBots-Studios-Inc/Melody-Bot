package com.disbots.commands.system;

import com.disbots.utilities.EmbedColors;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.lang.management.ManagementFactory;

public class Uptime implements CommandExecutor
{

    @Command(aliases = {"uptime", "up"}, description = "Shows how long the bot has been up for.", usage = "uptime")
    public void OnUptime(MessageCreateEvent message)
    {
        EmbedBuilder UptimeEmbed = new EmbedBuilder()
                .setTitle("Uptime")
                .setDescription("The bot has been online for " + formatUptime())
                .setColor(EmbedColors.NEUTRAL.getCode())
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
        message.getChannel().sendMessage(UptimeEmbed);
    }

    public static String formatUptime()
    {
        //Taken from Almighty Alpaca
        //https://github.com/Java-Discord-Bot-System/Plugin-Uptime/blob/master/src/main/java/com/almightyalpaca/discord/bot/plugin/uptime/UptimePlugin.java#L28-L42

        /* Formating the uptime in the dd:hh:mm:ss format. */
        final long duration = ManagementFactory.getRuntimeMXBean().getUptime();

        final long years = duration / 31104000000L;
        final long months = duration / 2592000000L % 12;
        final long days = duration / 86400000L % 30;
        final long hours = duration / 3600000L % 24;
        final long minutes = duration / 60000L % 60;
        final long seconds = duration / 1000L % 60;
        // final long milliseconds = duration % 1000;

        String uptime = (years == 0 ? "" : "**" + years + "** year(s), ") + (months == 0 ? "" : "**" + months + "** month(s), ") + (days == 0 ? "" : "**" + days + "** day(s), ") + (hours == 0 ? "" : "**" + hours + "** Hours, ")
                + (minutes == 0 ? "" : "**" + minutes + "** minute(s), ") + (seconds == 0 ? "" : "**" + seconds + "** second(s), ") /* + (milliseconds == 0 ? "" : milliseconds + " Milliseconds, ") */;

        uptime = replaceLast(uptime, ", ", "");
        uptime = replaceLast(uptime, ",", " and");
        return uptime;
    }

    //Taken from Almighty Alpaca
    //https://github.com/Java-Discord-Bot-System/Core/blob/master/src/main/java/com/almightyalpaca/discord/bot/system/util/StringUtils.java#L15-L17
    private static String replaceLast(final String text, final String regex, final String replacement)
    {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}
