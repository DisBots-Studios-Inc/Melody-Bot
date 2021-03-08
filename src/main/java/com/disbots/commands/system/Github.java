package com.disbots.commands.system;

import com.disbots.core.Main;
import com.disbots.utilities.EmbedColors;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Github implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent message) {
        if (message.getMessageContent().equalsIgnoreCase(Main.Prefix)) {
            EmbedBuilder githubEmbed = new EmbedBuilder()
                    .setTitle("GitHub Link")
                    .setDescription("If you want to view the bot's GitHub, you can click [here](https://github.com/DisBots-Studios-Inc/Melody-Bot)")
                    .setColor(EmbedColors.NEUTRAL.getCode())
                    .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar());
            message.getChannel().sendMessage(githubEmbed);
        }
    }
}
