package com.disbots.commands.information;

import com.disbots.core.Main;
import com.disbots.utilities.EmbedColors;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class BotInfo implements MessageCreateListener
{
    @Override
    public void onMessageCreate(MessageCreateEvent message)
    {
        if (message.getMessageContent().equalsIgnoreCase(Main.Prefix + "botinfo"))
        {
            SendEmbed(message);
        }
    }

    private void SendEmbed(MessageCreateEvent message)
    {
        EmbedBuilder BotInfoEmbed = new EmbedBuilder()
                .setTitle("Bot Information")
                .addField("Users", "Serving " + "**"+ message.getApi().getCachedUsers().size() +"**" + " users!", true)
                .addField("Channels", "Monitoring " + "**"+ message.getApi().getChannels().size() +"**" + " channels!", true)
                .addField("Servers", "Managing " + "**"+ message.getApi().getServers().size() +"**" + " servers!", true)
                .addField("Support", "If you want to join the support server, click [here](https://discord.gg/6g297Usrsn)")
                .addField("Invite", "If you want to add me to your server, click [here](" + message.getApi().createBotInvite() + ")!")
                .addField("From the creators", "I was created by DisBots Studios Inc. consisting of <@518382491338539017> and <@683879319558291539>. Incase of any problems contact them.")
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                .setColor(EmbedColors.NEUTRAL.getCode());

        message.getChannel().sendMessage(BotInfoEmbed);
    }
}
