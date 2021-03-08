package com.disbots.commands.information;

import com.disbots.commands.system.Uptime;
import com.disbots.core.Main;
import com.disbots.utilities.EmbedColors;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

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
                .addField("Uptime", Uptime.formatUptime(), false)
                .addField("The crew", "**Co-founders/Developers**\nAktindo (<@683879319558291539>)\nGame Glide (<@518382491338539017>)")
                .addField("Additional note", "The team is very grateful to you for inviting/voting/supporting it! We would love to bring out new features everyday!")
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                .setColor(EmbedColors.NEUTRAL.getCode());

        message.getChannel().sendMessage(BotInfoEmbed);
    }
}
