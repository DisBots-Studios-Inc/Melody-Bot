package com.disbots.commands.help;

import com.disbots.core.Main;
import com.disbots.utilities.EmbedColors;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Help implements MessageCreateListener
{

    @Override
    public void onMessageCreate(MessageCreateEvent message)
    {
        if (message.getMessageContent().equalsIgnoreCase(Main.Prefix + "help")) {
            EmbedBuilder HelpEmbed = new EmbedBuilder()
                    .setTitle("Help Menu")
                    .addField("Here are the commands!", "", false)
                    .setDescription(
                            // TODO: make this dynamic using listeners
                            ""
                    );
            message.getChannel().sendMessage(HelpEmbed);
        }
    }
}
