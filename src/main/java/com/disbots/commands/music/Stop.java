package com.disbots.commands.music;

import com.disbots.util.EmbedColors;
import com.disbots.util.logging.Log;
import com.disbots.util.logging.LogTypes;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

public class Stop implements CommandExecutor
{
    @Command(aliases = {"stop", "s"}, description = "Stops playing music.", usage = "stop")
    public void OnStop(MessageCreateEvent message)
    {
        EmbedBuilder StopEmbed = new EmbedBuilder()
                .setTitle("Stopped playing! <a:green_tick:781083389280911370>")
                .setColor(EmbedColors.SUCCESS.getCode())
                .setFooter("**:warning: WARNING:** using this multiple times while it is stopping can break the bot!", message.getMessageAuthor().getAvatar());

        message.getChannel().sendMessage(StopEmbed);

        ServerVoiceChannel voiceChannel = message.getMessageAuthor().getConnectedVoiceChannel().get();
        voiceChannel.connect().thenAccept(AudioConnection::close);
        message.getServer().get().moveUser(message.getApi().getYourself(), null);

        new Log().log(LogTypes.INFO, "Stopped playing music", "Stop_Command");
    }
}
