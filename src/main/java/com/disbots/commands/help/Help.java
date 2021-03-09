package com.disbots.commands.help;

import com.disbots.core.Main;
import com.disbots.utilities.EmbedColors;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

public class Help implements CommandExecutor
{
    private final CommandHandler commandHandler;

    public Help(CommandHandler commandHandler)
    {
        this.commandHandler = commandHandler;
    }

    @Command(aliases = {"help", "commands"}, description = "Shows All the commands of the bot", usage = "help")
    public String OnHelpCommand()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("```xml"); // a xml code block looks fancy
        for (CommandHandler.SimpleCommand simpleCommand : commandHandler.getCommands())
        {
            if (!simpleCommand.getCommandAnnotation().showInHelpPage())
            {
                continue; // skip command
            }
            builder.append("\n");
            if (!simpleCommand.getCommandAnnotation().requiresMention())
            {
                // the default prefix only works if the command does not require a mention
                builder.append(commandHandler.getDefaultPrefix());
            }
            String usage = simpleCommand.getCommandAnnotation().usage();
            if (usage.isEmpty())
            { // no usage provided, using the first alias
                usage = simpleCommand.getCommandAnnotation().aliases()[0];
            }
            builder.append(usage);
            String description = simpleCommand.getCommandAnnotation().description();
            if (!description.equals("none"))
            {
                builder.append(" | ").append(description);
            }
        }
        builder.append("\n```"); // end of xml code block
        return builder.toString();
    }


//    public void OnHelp(MessageCreateEvent message)
//    {
//        EmbedBuilder HelpEmbed = new EmbedBuilder()
//                .setTitle("Help Menu")
//                .addField("Here are the commands!", "", false)
//                .setDescription(Commands())
//                .setColor(EmbedColors.NEUTRAL.getCode());
//        message.getChannel().sendMessage(HelpEmbed);
//    }
}
