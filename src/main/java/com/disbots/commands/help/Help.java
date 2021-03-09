package com.disbots.commands.help;

import com.disbots.util.EmbedColors;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Help implements CommandExecutor
{
    private final CommandHandler commandHandler;

    public Help(CommandHandler commandHandler)
    {
        this.commandHandler = commandHandler;
    }

    @Command(aliases = {"help", "commands", "h"}, description = "Shows All the commands of the bot", usage = "help")
    public void OnHelpCommand(MessageCreateEvent message)
    {
        EmbedBuilder helpEmbed = new EmbedBuilder()
                .setTitle("Help menu")
                .setDescription("Here all the commands, the current prefix for this server is `;`.")
                .setFooter(message.getMessageAuthor().getDisplayName(), message.getMessageAuthor().getAvatar())
                .setColor(EmbedColors.NEUTRAL.getCode());
        for (CommandHandler.SimpleCommand simpleCommand : commandHandler.getCommands())
        {
            String prefix = "<@767729072540221491>";
            if (!simpleCommand.getCommandAnnotation().showInHelpPage())
            {
                continue; // skip command
            }
            if (!simpleCommand.getCommandAnnotation().requiresMention())
            {
                prefix = commandHandler.getDefaultPrefix();
            }
            String name = simpleCommand.getCommandAnnotation().aliases()[0];

            List<String> aliases = new ArrayList<String>(Arrays.asList(simpleCommand.getCommandAnnotation().aliases()));
            aliases.remove(0);

            String usage = simpleCommand.getCommandAnnotation().usage();
            if (usage.isEmpty())
            { // no usage provided, using the first alias
                usage = simpleCommand.getCommandAnnotation().aliases()[0];
            }
            String description = simpleCommand.getCommandAnnotation().description();
            if (description.equals("none"))
            {
                description = "None";
            }

            helpEmbed.addField("Command - " + name, "**Description:** " + description + "\n" + "**Aliases:** " + String.join(", ", aliases) + "\n" + "**Usage:** " + "`" + prefix + usage + "`", true);
        }
        message.getChannel().sendMessage(helpEmbed);
    }
}
