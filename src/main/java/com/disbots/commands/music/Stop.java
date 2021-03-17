package com.disbots.commands.music;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.event.message.MessageCreateEvent;

public class Stop implements CommandExecutor
{
    @Command(aliases = {"stop", "s"}, description = "Stops playing music.", usage = "stop")
    public void OnStop(MessageCreateEvent message)
    {
        ServerVoiceChannel voiceChannel = message.getMessageAuthor().getConnectedVoiceChannel().get();
        message.getServer().get().moveUser(message.getApi().getYourself(), voiceChannel);
    }
}
