package com.disbots.commands.system;

import com.disbots.utilities.EmbedColors;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

public class Blogs implements CommandExecutor
{
    @Command(aliases = {"blog", "blogs"}, description = "Links to the Melody blog", usage = "blog")
    public void BlogEmbed(MessageCreateEvent message)
    {

        EmbedBuilder blogEmbed = new EmbedBuilder()
                .setTitle("Blogs Link")
                .setDescription("The blog for Melody is [here](https://melody-blogs.netlify.app/about/)")
                .setColor(EmbedColors.NEUTRAL.getCode())
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
        message.getChannel().sendMessage(blogEmbed);
    }
}
