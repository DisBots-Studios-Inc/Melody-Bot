package com.disbots.commands.system;

import com.disbots.util.EmbedColors;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

public class Github implements CommandExecutor
{
    @Command(aliases = {"github", "git"}, description = "Shows the github Link of the bot", usage = "github")
    public void GithubEmbed(MessageCreateEvent message)
    {
        /* Github Embed, Noting much here :) */

        EmbedBuilder githubEmbed = new EmbedBuilder()
                .setTitle("GitHub Link")
                .setDescription("If you want to view the bot's GitHub, you can click [here](https://github.com/DisBots-Studios-Inc/Melody-Bot)")
                .setColor(EmbedColors.NEUTRAL.getCode())
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
        message.getChannel().sendMessage(githubEmbed);
    }
}
