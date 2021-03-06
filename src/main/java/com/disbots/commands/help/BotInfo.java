package com.disbots.commands.help;

import com.disbots.core.Main;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
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
        Color EmbedColor = new Color(255, 86, 87);

        EmbedBuilder BotInfoEmbed = new EmbedBuilder()
                .setTitle("**Bot Information**")
                .addField("**Users**", String.valueOf(message.getApi().getCachedUsers().size()))
                .addField("**Channels**", String.valueOf(message.getApi().getChannels().size()))
                .addField("**Servers**", String.valueOf(message.getApi().getServers().size()))
                .addField("**Support**", "No support server yet :(")
                .addField("**Invite**", "If you want to add me to your server, click [this](" + message.getApi().createBotInvite() + ")!")
                .addField("**My Creators!**", "I was created by DisBots Studios inc. consisting of <@518382491338539017> and <@683879319558291539>, for any problems contact them (Fun fact: They are idiots! Don't tell then I said that!)")
                .setFooter("", message.getMessageAuthor().getAvatar())
                .setColor(EmbedColor);

        message.getChannel().sendMessage(BotInfoEmbed);
    }
}
